package com.vitsed.myTestApp.ui.view.student;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
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

    public StudentForm() {
        addClassName("student-form");
        add(firstName,
            lastName,
            patronymic,
            yearOfBirth,
            studentGroup, buttonsLayout.createFormButtonsLayout());
    }
}
