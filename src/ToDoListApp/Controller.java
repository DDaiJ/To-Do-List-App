package ToDoListApp;

import ToDoListApp.dataModel.TodoData;
import ToDoListApp.dataModel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;


import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetails;
    @FXML
    private Label deadLineLabel;

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
    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetails.setText("\n" + item.getDetails());
        deadLineLabel.setText(item.getDeadline().toString());
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due: ");
//        sb.append(item.getDeadline().toString());
//        itemDetails.setText(sb.toString());
    }


}
