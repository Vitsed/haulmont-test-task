package com.vitsed.myTestApp.ui.view.student;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vitsed.myTestApp.backend.entity.Student;
import com.vitsed.myTestApp.backend.service.StudentService;
import com.vitsed.myTestApp.ui.MainLayout;
import com.vitsed.myTestApp.ui.view.ButtonsLayout;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Students | Student App")
public class StudentView extends VerticalLayout {

    private Grid<Student> grid = new Grid<>(Student.class);
    private  TextField filterByLastName;
    private  TextField filterByGroupNumber;
    private ButtonsLayout buttonsLayout = new ButtonsLayout();
    private StudentForm form;

    private StudentService studentService;

    public StudentView(StudentService studentService) {
        this.studentService = studentService;
        addClassName("student-view");
        setSizeFull();


        configureGrid();

//        form = new StudentForm();

        add(new H1("Список студентов"),getToolBar(), grid, buttonsLayout.createViewButtonsLayout());
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


    private HorizontalLayout getToolBar() {
        filterByLastName = new TextField();
        filterByLastName.setPlaceholder("По фамилии...");
        filterByLastName.setClearButtonVisible(true);
//        filterByLastName.setValueChangeMode(ValueChangeMode.LAZY);
//        filterByLastName.addValueChangeListener(e -> updateList());

        filterByGroupNumber = new TextField();
        filterByGroupNumber.setPlaceholder("По номеру группы...");
        filterByGroupNumber.setClearButtonVisible(true);
//        filterByGroupNumber.setValueChangeMode(ValueChangeMode.LAZY);
//        filterByGroupNumber.addValueChangeListener(e -> updateList());

        Button applyButton = new Button("Применить", e -> {
            updateList(filterByLastName, filterByGroupNumber);
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterByLastName, filterByGroupNumber, applyButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void editStudent(Student student) {
        if (student == null) {
            closeEditor();
        } else {

        }
    }

    private void closeEditor() {

    }

    private void updateList() {
        grid.setItems(studentService.findAll());
    }

    private void updateList(TextField field, TextField numberField) {
        String lastName = null;
        int groupNumber = 0;

        if(!field.isEmpty()) {
            lastName = field.getValue();
        }
        if(!numberField.isEmpty()) {
            groupNumber = Integer.parseInt(numberField.getValue());
        }

        if(lastName == null && groupNumber == 0) {
            updateList();
        } else if(groupNumber != 0 && lastName == null) {
            grid.setItems(studentService.findAll(groupNumber));
        } else if (groupNumber == 0) {
            grid.setItems(studentService.findAll(lastName));
        } else {
            grid.setItems(studentService.findAll(lastName, groupNumber));
        }
    }
}
