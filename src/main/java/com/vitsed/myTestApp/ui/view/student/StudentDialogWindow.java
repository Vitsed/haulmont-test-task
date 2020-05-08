package com.vitsed.myTestApp.ui.view.student;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vitsed.myTestApp.backend.entity.Student;
import com.vitsed.myTestApp.backend.service.GroupService;
import com.vitsed.myTestApp.backend.service.StudentService;
import com.vitsed.myTestApp.utils.names.Designation;

public class StudentDialogWindow extends Dialog {

    TextField firstName = new TextField(Designation.FIRST_NAME);
    TextField lastName = new TextField(Designation.LAST_NAME);
    TextField patronymic = new TextField(Designation.PATRONYMIC);
    TextField yearOfBirth = new TextField(Designation.YEAR_OF_BIRTH);
    TextField studentGroup = new TextField(Designation.GROUP_NUMBER);
    Student student;
    Button ok = new Button(Designation.BUTTON_OK);
    Button cancel = new Button(Designation.BUTTON_CANCEL);
    boolean flag;

    StudentService studentService;
    Binder<Student> binder = new BeanValidationBinder<>(Student.class);

    public StudentDialogWindow(StudentService studentService, GroupService groupService) {

        this.studentService = studentService;
        binder.forField(firstName).withValidator(firstName -> firstName.length() <= 255, "Имя не может быть длиннее 255 символов").bind(Student::getFirstName, Student::setFirstName);
        binder.bind(lastName, Student::getLastName, Student::setLastName);
        binder.bind(patronymic, Student::getPatronymic, Student::setPatronymic);
        binder.forField(yearOfBirth).withConverter(new StringToIntegerConverter("Укажите год рождения")).bind(Student::getYearOfBirth, Student::setYearOfBirth);

        setWidth("800px");
        setHeight("500px");

        add(new FormLayout(lastName,
            firstName,
            patronymic,
            yearOfBirth,
            studentGroup, createButtonsLayout()));
    }

    private Component createButtonsLayout() {
        ok.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        ok.addClickListener(click -> {
            if(flag) {
                studentService.save(student);
            } else {
                studentService.delete(student);
            }
            clearFields();
            close();
        });

        cancel.addClickListener(click -> {
            clearFields();
            close();
        });
        cancel.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(ok, cancel);
    }

    private void clearFields() {
        lastName.clear();
        firstName.clear();
        patronymic.clear();
        yearOfBirth.clear();
        studentGroup.clear();
    }

    //Events
    public void addStudent(Student student, boolean flag) {
        binder.setBean(student);
        this.student = student;
        this.flag = flag;
    }
}
