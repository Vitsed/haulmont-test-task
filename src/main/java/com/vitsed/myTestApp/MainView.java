package com.vitsed.myTestApp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vitsed.myTestApp.backend.entity.Student;
import com.vitsed.myTestApp.backend.service.StudentService;

@Route("")
public class MainView extends VerticalLayout {

    private StudentService contactService;
    private Grid<Student> grid = new Grid<>(Student.class);
    private  TextField filterByLastName;
    private  TextField filterByGroupNumber;
    // Кнопки
    private Button add = new Button("Добавить");
    private Button edit = new Button("Изменить");
    private Button delete = new Button("Удалить");

    public MainView(StudentService contactService) {
        HorizontalLayout filterLayout = new HorizontalLayout();
        filterByLastName = new TextField("Filter by last name...");
        filterByGroupNumber = new TextField("Filter by group number...");
        filterLayout.add(filterByLastName, filterByGroupNumber);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.add(add, edit, delete);
        this.contactService = contactService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(filterLayout, grid, buttonsLayout);
        updateList();
    }

    private void configureGrid() {
        grid.addClassName("student-grid");
        grid.setSizeFull();
        grid.removeAllColumns();
        grid.addColumn(Student::getLastName).setHeader("Фамилия").setSortable(true);
        grid.addColumn(Student::getFirstName).setHeader("Имя");
        grid.addColumn(Student::getPatronymic).setHeader("Отчество");
        grid.addColumn(Student::getYearOfBirth).setHeader("Год рождения");
        grid.addColumn(Student::getStudentGroup).setHeader("Группа");
    }

    private void updateList() {
        grid.setItems(contactService.findAll());
    }

}
