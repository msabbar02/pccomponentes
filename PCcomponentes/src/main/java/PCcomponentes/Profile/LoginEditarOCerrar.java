package PCcomponentes.Profile;

import PCcomponentes.Productos.MYSQL;
import PCcomponentes.Productos.ProductoClienteControlador;
import PCcomponentes.Productos.ProductoControlador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginEditarOCerrar {

    private ProductoControlador productoControlador;
    private ProductoClienteControlador productoClienteControlador;

    public void setProductoControlador(ProductoControlador productoControlador) {
        this.productoControlador = productoControlador;
    }
    public void setProductoClienteControlador(ProductoClienteControlador productoClienteControlador) {
        this.productoClienteControlador = productoClienteControlador;
    }

    @FXML
    private void handleEditar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginEditar.fxml"));
            Parent root = loader.load();

            PCcomponentes.Profile.LoginEditar loginEditarController = loader.getController();
            loginEditarController.setProductoControlador(productoControlador);
            loginEditarController.setProductoClienteControlador(productoClienteControlador);


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCerrar(ActionEvent event) throws IOException {
        closeProductWindow(event);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void closeProductWindow(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();

        if (productoControlador != null) {
            productoControlador.closeWindow(sourceNode);
        } else if (productoClienteControlador != null) {
            productoClienteControlador.closeWindow(sourceNode);
        }

        stage.close();
    }


    @FXML
    private void handleEliminar(ActionEvent event) {
        String username = MYSQL.getUsername();
        MYSQL.deleteUserFromDatabase(username);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
