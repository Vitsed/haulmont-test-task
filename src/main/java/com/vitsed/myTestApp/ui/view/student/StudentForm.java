package com.vitsed.myTestApp.ui.view.student;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vitsed.myTestApp.backend.entity.Student;
import com.vitsed.myTestApp.ui.MainLayout;
import com.vitsed.myTestApp.ui.view.ButtonsLayout;
import com.vitsed.myTestApp.utils.names.Designation;

@Route(value = "studentForm", layout = MainLayout.class)
@PageTitle("StudentForm")
public class StudentForm extends FormLayout {

    TextField firstName = new TextField(Designation.FIRST_NAME);
    TextField lastName = new TextField(Designation.LAST_NAME);
    TextField patronymic = new TextField(Designation.PATRONYMIC);
    TextField yearOfBirth = new TextField(Designation.YEAR_OF_BIRTH);
    TextField studentGroup = new TextField(Designation.GROUP_NUMBER);
    ButtonsLayout buttonsLayout = new ButtonsLayout();

    Binder<Student> binder = new BeanValidationBinder<>(Student.class);

    public StudentForm() {
        addClassName("student-form");
//        binder.bindInstanceFields(this);

        add(firstName,
            lastName,
            patronymic,
            yearOfBirth,
            studentGroup, buttonsLayout.createFormButtonsLayout());
    }

    public void setStudent(Student student) {binder.setBean(student);}

    private void validateAndSave() {
        if(binder.isValid()) {

        }
    }
    //Events
    public static abstract class StudentFormEvent extends ComponentEvent<StudentForm> {
        private Student student;

        public StudentFormEvent(StudentForm source, Student student) {
            super(source, false);
            this.student = student;
        }

        public Student getStudent() {
            return student;
        }
    }



}
