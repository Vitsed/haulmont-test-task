package com.vitsed.myTestApp.ui.view.student;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vitsed.myTestApp.backend.entity.Student;
import com.vitsed.myTestApp.backend.service.StudentService;
import com.vitsed.myTestApp.ui.MainLayout;
import com.vitsed.myTestApp.ui.view.ButtonsLayout;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Students | Student App")
public class StudentView extends VerticalLayout {

    private StudentService contactService;
    private Grid<Student> grid = new Grid<>(Student.class);
    private  TextField filterByLastName;
    private  TextField filterByGroupNumber;
    private ButtonsLayout buttonsLayout = new ButtonsLayout();
    private StudentForm form;


    public StudentView(StudentService contactService) {
        this.contactService = contactService;
        addClassName("student-view");
        setSizeFull();

        HorizontalLayout filterLayout = new HorizontalLayout();
        filterByLastName = new TextField();
        filterByLastName.setPlaceholder("По фамилии...");
        filterByGroupNumber = new TextField();
        filterByGroupNumber.setPlaceholder("По номеру группы...");
        Button applyButton = new Button("Применить");

        filterLayout.add(filterByLastName, filterByGroupNumber, applyButton);
        filterLayout.addClassName("filter-view");
        filterLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        configureGrid();

//        form = new StudentForm();

        add(new H1("Список студентов"),filterLayout, grid, buttonsLayout.createViewButtonsLayout());
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
