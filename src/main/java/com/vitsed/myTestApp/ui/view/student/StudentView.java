package com.vitsed.myTestApp.ui.view.student;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vitsed.myTestApp.backend.entity.Student;
import com.vitsed.myTestApp.backend.service.StudentService;
import com.vitsed.myTestApp.ui.MainLayout;
import com.vitsed.myTestApp.utils.names.Designation;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Students | Student App")
public class StudentView extends VerticalLayout {

    private Grid<Student> grid = new Grid<>(Student.class);
    private TextField filterByLastName;
    private NumberField filterByGroupNumber;
    private Button add = new Button(Designation.BUTTON_ADD);
    private Button edit = new Button(Designation.BUTTON_EDIT);
    private Button delete = new Button(Designation.BUTTON_DELETE);
    private StudentDialogWindow dialogWindow = new StudentDialogWindow();

    private StudentService studentService;

    public StudentView(StudentService studentService) {
        this.studentService = studentService;
        addClassName("student-view");
        setSizeFull();

        configureGrid();

        add(new H1("Список студентов"),getToolBar(), grid, createButtonsLayout());
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
        filterByLastName.setPlaceholder("Фамилия...");
        filterByLastName.setClearButtonVisible(true);
//        filterByLastName.setValueChangeMode(ValueChangeMode.LAZY);
//        filterByLastName.addValueChangeListener(e -> updateList());

        filterByGroupNumber = new NumberField();
        filterByGroupNumber.setPlaceholder("Номер группы...");
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

    private void openDialogWindow() {
        dialogWindow.open();
    }


    private Component createButtonsLayout() {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        edit.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        add.addClickShortcut(Key.ENTER);
        edit.addClickShortcut(Key.ESCAPE);

        add.addClickListener(click -> dialogWindow.open());

        return new HorizontalLayout(add, edit, delete);
    }


    private void addStudent() {

    }

    private void editStudent(Student student) {

    }

    private void deleteStudent(Student student) {

    }


    private void updateList() {
        grid.setItems(studentService.findAll());
    }

    private void updateList(TextField field, NumberField numberField) {
        String lastName = null;
        int groupNumber = 0;

        if(!field.isEmpty()) {
            lastName = field.getValue();
        }
        if(!numberField.isEmpty()) {
            groupNumber = (int)numberField.getValue().longValue();
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
