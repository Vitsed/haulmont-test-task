package com.vitsed.myTestApp.ui.view.group;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vitsed.myTestApp.ui.MainLayout;
import com.vitsed.myTestApp.ui.view.ButtonsLayout;
import com.vitsed.myTestApp.utils.names.Designation;

@Route(value = "GroupForm", layout = MainLayout.class)
@PageTitle("GroupForm")
public class GroupForm extends FormLayout {

    private TextField groupNumber = new TextField(Designation.GROUP_NUMBER);
    private TextField facultyName = new TextField(Designation.FACULTY_NAME);

    public GroupForm() {
        addClassName("group-form");
        add(groupNumber, facultyName, new ButtonsLayout().createFormButtonsLayout());
    }

}
