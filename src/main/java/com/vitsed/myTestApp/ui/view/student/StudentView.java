package com.vitsed.myTestApp.ui.view.student;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vitsed.myTestApp.backend.entity.Student;
import com.vitsed.myTestApp.backend.service.GroupService;
import com.vitsed.myTestApp.backend.service.StudentService;
import com.vitsed.myTestApp.ui.MainLayout;
import com.vitsed.myTestApp.utils.names.Designation;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Students | Student App")
public class StudentView extends VerticalLayout {

    private final Grid<Student> grid = new Grid<>(Student.class);
    private TextField filterByLastName;
    private TextField filterByGroupNumber;
    private final Button add = new Button(Designation.BUTTON_ADD);
    private final Button edit = new Button(Designation.BUTTON_EDIT);
    private final Button delete = new Button(Designation.BUTTON_DELETE);
    private Student student;
    private final StudentDialogWindow dialogWindow;

    private final StudentService studentService;

    public StudentView(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        addClassName("student-view");
        setSizeFull();

        dialogWindow = new StudentDialogWindow(studentService, groupService);
        configureGrid();

        add(new H1("Список студентов"),getToolBar(), grid, createButtonsLayout());
        updateList();
    }

    private void configureGrid() {
        grid.addClassName("student-grid");
        grid.setSizeFull();
        grid.removeAllColumns();
        grid.addColumn(Student::getLastName).setHeader("Фамилия");
        grid.addColumn(Student::getFirstName).setHeader("Имя");
        grid.addColumn(Student::getPatronymic).setHeader("Отчество");
        grid.addColumn(Student::getYearOfBirth).setHeader("Год рождения");
        grid.addColumn(Student::getStudentGroup).setHeader("Группа");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.addItemClickListener(item -> student = item.getItem());
    }


    private HorizontalLayout getToolBar() {
        filterByLastName = new TextField();
        filterByLastName.setPlaceholder("Фамилия...");
        filterByLastName.setClearButtonVisible(true);
        filterByLastName.setValueChangeMode(ValueChangeMode.LAZY);
//        filterByLastName.addValueChangeListener(e -> updateList());

        filterByGroupNumber = new TextField();
        filterByGroupNumber.setPlaceholder("Номер группы...");
        filterByGroupNumber.setClearButtonVisible(true);
        filterByGroupNumber.setValueChangeMode(ValueChangeMode.LAZY);
//        filterByGroupNumber.addValueChangeListener(e -> updateList());
        
        Button applyButton = new Button("Применить", e -> {
            updateList(filterByLastName, filterByGroupNumber);
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterByLastName, filterByGroupNumber, applyButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private Component createButtonsLayout() {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        edit.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        add.addClickListener(click -> {
            dialogWindow.addStudent(new Student(), true);
            dialogWindow.open();
        });

        edit.addClickListener(click -> {
            if(student != null) {
                dialogWindow.addStudent(student, true);
                dialogWindow.open();
            } else {
                Notification.show("Студент не выбран!");
            }
        });

        delete.addClickListener(click -> {
            if(student != null) {
                dialogWindow.addStudent(student, false);
                dialogWindow.open();
            } else {
                Notification.show("Студент не выбран!");
            }
        });

        return new HorizontalLayout(add, edit, delete);
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
