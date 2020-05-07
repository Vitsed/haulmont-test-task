package com.vitsed.myTestApp.ui.view.student;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import com.vitsed.myTestApp.backend.entity.Student;
import com.vitsed.myTestApp.utils.names.Designation;

public class StudentDialogWindow extends Dialog {

    TextField firstName = new TextField(Designation.FIRST_NAME);
    TextField lastName = new TextField(Designation.LAST_NAME);
    TextField patronymic = new TextField(Designation.PATRONYMIC);
    NumberField yearOfBirth = new NumberField(Designation.YEAR_OF_BIRTH);
    NumberField studentGroup = new NumberField(Designation.GROUP_NUMBER);

    Binder<Student> binder = new BeanValidationBinder<>(Student.class);

    public StudentDialogWindow() {
//        binder.bindInstanceFields(this);
        setWidth("800px");
        setHeight("500px");
        Button ok = new Button(Designation.BUTTON_OK);
        Button cancel = new Button(Designation.BUTTON_CANCEL, click -> close());
        HorizontalLayout buttonLayout = new HorizontalLayout(ok, cancel);
        add(new FormLayout(firstName,
            lastName,
            patronymic,
            yearOfBirth,
            studentGroup, buttonLayout));
    }

    public void setStudent(Student student) {binder.setBean(student);}

    private void validateAndSave() {
        if(binder.isValid()) {
        }
    }
    //Events
    public static abstract class StudentFormEvent extends ComponentEvent<StudentDialogWindow> {
        private Student student;

        public StudentFormEvent(StudentDialogWindow source, Student student) {
            super(source, false);
            this.student = student;
        }

        public Student getStudent() {
            return student;
        }
    }

    public static class CloseEvent extends StudentFormEvent {
        CloseEvent(StudentDialogWindow source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }


}
