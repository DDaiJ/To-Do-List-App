package ToDoListApp;

import ToDoListApp.dataModel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetails;

    public void initialize() {
        TodoItem item1 = new TodoItem("French HW", "MyFrenchLab: 9.25, 9.26, 9.27",
                LocalDate.of(2018, Month.NOVEMBER, 20));
        TodoItem item2 = new TodoItem("CSE 311", "Finish today's panapto",
                LocalDate.of(2018, Month.NOVEMBER, 15));
        TodoItem item3 = new TodoItem("Safeway", "buy fruits and tomatoes and chicken",
                LocalDate.of(2018, Month.NOVEMBER, 16));
        TodoItem item4 = new TodoItem("Lady M", "@Pacific Place 12:00 - 5:00",
                LocalDate.of(2018, Month.NOVEMBER, 17));
        TodoItem item5 = new TodoItem("Study French", "study for Monday's French quiz",
                LocalDate.of(2018, Month.NOVEMBER, 18));

        todoItems = new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        StringBuilder sb = new StringBuilder(item.getDetails());
        sb.append("\n\n\n\n");
        sb.append("Due: ");
        sb.append(item.getDeadline().toString());
        itemDetails.setText(sb.toString());
    }


}
