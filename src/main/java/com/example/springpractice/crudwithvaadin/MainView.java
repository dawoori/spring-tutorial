package com.example.springpractice.crudwithvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

@Route
public class MainView extends VerticalLayout {

    private final StudentRepository repository;

    private final StudentEditor editor;

    final Grid<Student> grid;

    final TextField filter;

    private final Button addNewBtn;

    public MainView(StudentRepository repository, StudentEditor editor) {
        this.repository = repository;
        this.editor = editor;
        this.grid = new Grid<>(Student.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New Student", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("id", "firstName", "lastName");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filtered by last name");

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listStudents(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.setVisible(false);
            listStudents(filter.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editStudent(new Student("", "")));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listStudents(filter.getValue());
        });

        listStudents(null);
    }

    private void listStudents(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repository.findAll());
        } else {
            grid.setItems(repository.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }
}
