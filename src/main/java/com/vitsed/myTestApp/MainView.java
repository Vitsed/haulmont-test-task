package com.vitsed.myTestApp;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vitsed.myTestApp.backend.entity.Student;
import com.vitsed.myTestApp.backend.service.StudentService;


@Route("")
public class MainView extends VerticalLayout {

    private StudentService contactService;
    private Grid<Student> grid = new Grid<>(Student.class);

    public MainView(StudentService contactService) {
        this.contactService = contactService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(grid);
        updateList();
    }

    private void configureGrid() {
        grid.addClassName("student-grid");
        grid.setSizeFull();
        grid.setColumns("lastName", "firstName", "patronymic", "yearOfBirth", "studentGroup");
    }

    private void updateList() {
        grid.setItems(contactService.findAll());
    }

}
