package ToDoListApp;

import ToDoListApp.dataModel.TodoData;
import ToDoListApp.dataModel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Controller {

    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetails;
    @FXML
    private Label deadLineLabel;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize() {

//        TodoItem item1 = new TodoItem("French HW", "MyFrenchLab: 9.25, 9.26, 9.27",
//                LocalDate.of(2018, Month.NOVEMBER, 20));
//        TodoItem item2 = new TodoItem("CSE 311", "Finish today's panapto",
//                LocalDate.of(2018, Month.NOVEMBER, 15));
//        TodoItem item3 = new TodoItem("Safeway", "buy fruits and tomatoes and chicken",
//                LocalDate.of(2018, Month.NOVEMBER, 16));
//        TodoItem item4 = new TodoItem("Lady M", "@Pacific Place 12:00 - 5:00",
//                LocalDate.of(2018, Month.NOVEMBER, 17));
//        TodoItem item5 = new TodoItem("Study French", "study for Monday's French quiz",
//                LocalDate.of(2018, Month.NOVEMBER, 18));
//
//        todoItems = new ArrayList<TodoItem>();
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//        todoItems.add(item5);
//
//        TodoData.getInstance().setTodoItems(todoItems);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if (newValue != null) {
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetails.setText("\n" + item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadLineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

        todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();
    }

    @FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
//            Parent root = FXMLLoader.load(getClass().getResource("todoItemDialog.fxml"));
////            dialog.getDialogPane().setContent(root);
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            controller.processResult();
            todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
            System.out.println("Ok pressed");
        } else {
            System.out.println("cancel pressed");
        }
    }

    @FXML
    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetails.setText("\n" + item.getDetails());
        deadLineLabel.setText(item.getDeadline().toString());
        /*
        StringBuilder sb = new StringBuilder(item.getDetails());
        sb.append("\n\n\n\n");
        sb.append("Due: ");
        sb.append(item.getDeadline().toString());
        itemDetails.setText(sb.toString());
        */
    }

}
