package com.vietqr.org.service;

import com.vietqr.org.SyncDatabaseServiceGrpc;
import com.vietqr.org.SynchDataProto;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.UUID;

@Service
public class SyncDatabaseServiceImpl extends SyncDatabaseServiceGrpc.SyncDatabaseServiceImplBase {
    @Value("${source.database.url}")
    private String sourceDbUrl;

    @Value("${source.database.username}")
    private String sourceDbUsername;

    @Value("${source.database.password}")
    private String sourceDbPassword;

    @Value("${destination.database.url}")
    private String desDbUrl;

    @Value("${destination.database.username}")
    private String desDbUsername;

    @Value("${destination.database.password}")
    private String desDbPassword;
    @Override
    public void syncDatabase(SynchDataProto.SyncDatabaseRequest request, StreamObserver<SynchDataProto.SyncDatabaseResponse> responseObserver) {

        String date = request.getDate();


        boolean isSuccess = syncDatabaseLogic(date);

        SynchDataProto.SyncDatabaseResponse.Builder responseBuilder = SynchDataProto.SyncDatabaseResponse.newBuilder();
        if (isSuccess) {
            responseBuilder.setStatus("SUCCESS").setMessage("Database synchronization completed.");
        } else {
            responseBuilder.setStatus("FAILURE").setMessage("Database synchronization failed.");
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    private boolean syncDatabaseLogic(String date) {

        System.out.println("Source DB URL: " + sourceDbUrl);
        System.out.println("Destination DB URL: " + desDbUrl);
        try(Connection sourceConnection = DriverManager.getConnection(sourceDbUrl, sourceDbUsername, sourceDbPassword);
            Connection desConnection = DriverManager.getConnection(desDbUrl, desDbUsername, desDbPassword)){

            // lay database tu database source
            String selectQuery ="SELECT COUNT(id) AS sum FROM viet_qr.transaction_receive ;";
            PreparedStatement selectStmt = sourceConnection.prepareStatement(selectQuery);

            ResultSet resultSet = selectStmt.executeQuery();

            // chen du lieu vao database dich
            String insertQuery = "INSERT INTO vietqr_statistical.tr_date (id, br_count) VALUES (?,?);";
            PreparedStatement insertStmt = desConnection.prepareStatement(insertQuery);

            while(resultSet.next()){
                String uniqueId = UUID.randomUUID().toString(); // Generate a unique ID
                insertStmt.setString(1, uniqueId);
                insertStmt.setString(2, resultSet.getString("sum"));
               // insertStmt.setString(1, resultSet.getString("sum"));
                // co the insert nhieu cot khac nua

                insertStmt.addBatch();

            }
            // thuc thi batch insert
            insertStmt.executeBatch();

            // dong cac tai nguyen
            resultSet.close();
            selectStmt.close();
            insertStmt.close();


            return true; //dong bo thanh cong


        } catch (SQLException e){
            e.printStackTrace();
            return false;  // dong bo that bai
        }

    }
}
