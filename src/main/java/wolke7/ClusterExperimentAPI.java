package wolke7;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * REST API for creating cluster experiment resources.
 */
@Path("cluster-experiments")
public class ClusterExperimentAPI {
    private static final String            ATTRIBUTE_ALGORITHM                    = "algorithm";
    private static final String            ATTRIBUTE_START_NODE                   = "startNode";
    private static final String            ATTRIBUTE_FROM_COUNT                   = "fromCount";
    private static final String            ATTRIBUTE_TO_COUNT                     = "toCount";
    private static final String            ATTRIBUTE_STEP_SIZE                    = "stepSize";
    private static final int               HTTP_STATUS_CODE_BAD_REQUEST           = 400;
    private static final String            ERROR_JSON_SYNTAX                      = "Error in the JSON syntax. Make sure that every mandatory attribute (startNode, fromCount, toCount, stepSize) is used with the correct value type.";
    private static final String            ERROR_ATTRIBUTE_VALUE_EMPTY            = "An attribute value is empty. Make sure that every mandatory attribute (startNode, fromCount, toCount, stepSize) has a value.";
    private static final String            ERROR_ATTRIBUTE_VALUE_NEGATIVE_OR_ZERO = "An attribute value is <= 0. Make sure to use the correct attribute values.";
    //RabbitMQ
    private final static String            QUEUE_NAME                             = "RabbitQ";
    private final static String            VCAP_SERVICES                          = System.getenv("VCAP_SERVICES");
    private              ConnectionFactory factory                                = null;

    @POST
    @Consumes(APPLICATION_JSON)
    public Response createClusterExperiment(String input) throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException, IOException {
        try {
            JSONObject jsonObject = new JSONObject(input);

            String startNode = jsonObject.getString(ATTRIBUTE_START_NODE);
            StringEmptyException.checkStringNotEmpty(startNode);

            int fromCount = jsonObject.getInt(ATTRIBUTE_FROM_COUNT);
            NumberNegativeOrZeroException.checkNumberPositive(fromCount);

            int toCount = jsonObject.getInt(ATTRIBUTE_TO_COUNT);
            NumberNegativeOrZeroException.checkNumberPositive(toCount);

            int stepSize = jsonObject.getInt(ATTRIBUTE_STEP_SIZE);
            NumberNegativeOrZeroException.checkNumberPositive(stepSize);

            JSONObject vcap = getVcapVariable();

            // Getting ALL RabbitMQ Credentials from VCAP-Variable
            JSONArray rabbitArray = vcap.getJSONArray("rabbitmq-2.8");

            // Getting rabbitMQ AMQP Link from VCAP_SERVICES
            String rabbitAMQP = null;
            for (int i = 0; i < rabbitArray.length(); i++) {
                JSONObject rabbitObject = rabbitArray.getJSONObject(i);
                JSONObject rabbitCredentials = rabbitObject.getJSONObject("credentials");
                rabbitAMQP = rabbitCredentials.getString("url");
            }

            // Initializing Connection to RabbitMQ
            ConnectionFactory factory = new ConnectionFactory();
            System.out.println("Trying to connect RabbitMQ");
            factory.setUri(rabbitAMQP);
            System.out.println("AMQP URI: " + rabbitAMQP);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            jsonObject.put(ATTRIBUTE_ALGORITHM, "NHC");
            channel.basicPublish("", QUEUE_NAME, null, jsonObject.toString().getBytes());
            System.out.println("GESENDET:  '" + jsonObject.toString() + "'");

            jsonObject.put(ATTRIBUTE_ALGORITHM, "KMeans");
            channel.basicPublish("", QUEUE_NAME, null, jsonObject.toString().getBytes());
            System.out.println("GESENDET:  '" + jsonObject.toString() + "'");

            jsonObject.put(ATTRIBUTE_ALGORITHM, "RNSC");
            channel.basicPublish("", QUEUE_NAME, null, jsonObject.toString().getBytes());
            System.out.println("GESENDET:  '" + jsonObject.toString() + "'");

            channel.close();
            connection.close();

            return Response.accepted().build();
        } catch (JSONException e) {
            return Response.status(HTTP_STATUS_CODE_BAD_REQUEST).entity(ERROR_JSON_SYNTAX).build();
        } catch (StringEmptyException e) {
            return Response.status(HTTP_STATUS_CODE_BAD_REQUEST).entity(ERROR_ATTRIBUTE_VALUE_EMPTY).build();
        } catch (NumberNegativeOrZeroException e) {
            return Response.status(HTTP_STATUS_CODE_BAD_REQUEST).entity(ERROR_ATTRIBUTE_VALUE_NEGATIVE_OR_ZERO).build();
        }
    }

    // Getting the Environment Variable "CVAP_SERVICES"
    public static JSONObject getVcapVariable() throws JSONException {
        System.out.println("Getting environment variables");

        JSONObject vcap = new JSONObject(VCAP_SERVICES);
        System.out.println(vcap);

        return vcap;
    }
}