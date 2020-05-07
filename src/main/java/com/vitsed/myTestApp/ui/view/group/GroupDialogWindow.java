package com.vitsed.myTestApp.ui.view.group;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vitsed.myTestApp.ui.MainLayout;
import com.vitsed.myTestApp.utils.names.Designation;

@Route(value = "GroupForm", layout = MainLayout.class)
@PageTitle("GroupForm")
public class GroupDialogWindow extends Dialog {

    private NumberField groupNumber = new NumberField(Designation.GROUP_NUMBER);
    private TextField facultyName = new TextField(Designation.FACULTY_NAME);
    private Button ok = new Button(Designation.BUTTON_OK);
    private Button cancel = new Button(Designation.BUTTON_CANCEL);

    public GroupDialogWindow() {
        HorizontalLayout buttonsLayout = new HorizontalLayout(ok, cancel);
        add(new FormLayout(groupNumber, facultyName, buttonsLayout));
    }

}
