package com.vietqr.org.service;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import com.google.gson.Gson;
public class VietQRClient {
    private static final String BROKER = "tcp://broker.hivemq.com:1883";
    private static final String CLIENT_ID = "VietQRClient";
    private static final String REQUEST_TOPIC = "vietqr/request";
    private static final String RESPONSE_TOPIC = "vietqr/response";
    private static final String NOTIFICATION_TOPIC = "notification/topic";
    //    private static final String USERNAME = "VietQR123"; // thêm username
//    private static final String PASSWORD = "VietQR123";
    private MqttClient client;

    public VietQRClient() throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        client = new MqttClient(BROKER, CLIENT_ID, persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
//        connOpts.setUserName(USERNAME);
//        connOpts.setPassword(PASSWORD.toCharArray());
        client.connect(connOpts);

        client.subscribe(RESPONSE_TOPIC, new IMqttMessageListener() {
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String response = new String(message.getPayload());
                System.out.println("Received response: " + response);
                // Xử lý phản hồi từ server
            }
        });
        // Đăng ký lắng nghe thông báo từ server
        client.subscribe(NOTIFICATION_TOPIC, new IMqttMessageListener() {
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String notification = new String(message.getPayload());
                System.out.println("Received notification: " + notification);
                // Xử lý thông báo từ server
            }
        });

    }

    public void sendRequest(VietQRCreateCustomerDTO dto) throws MqttException {
        Gson gson = new Gson();
        String payload = gson.toJson(dto);
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(2);
        client.publish(REQUEST_TOPIC, message);
        System.out.println("Request sent: " + payload);
    }

    public void disconnect() throws MqttException {
        client.disconnect();
    }


    public static void main(String[] args) {
        try {
            VietQRClient client = new VietQRClient();
            VietQRCreateCustomerDTO dto = new VietQRCreateCustomerDTO(10000L, "Dondathangsanpham", "0373568944", "MB",
                    "NGUYEN PHUONG NHAI LINH", "C", "5035Linh30", "123","A95", "01");
            client.sendRequest(dto);
        } catch (MqttException  e) {
            e.printStackTrace();
        }
    }
}
