package com.example.springpractice.crudwithvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

@Route
public class MainView extends VerticalLayout {
    private final StudentRepository repository;
    final Grid<Student> grid;

    public MainView(StudentRepository repository) {
        this.repository = repository;
        this.grid = new Grid<>(Student.class);
        TextField filter = new TextField();
        filter.setPlaceholder("Filter by last name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listStudents(e.getValue()));
        add(filter, grid);
//        add(new Button("Click me",e -> Notification.show("Hello, Spring + Vaddin user!")));
    }

    private void listStudents(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repository.findAll());
        } else {
            grid.setItems(repository.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }
}
