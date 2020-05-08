package com.vitsed.myTestApp.ui.view.group;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vitsed.myTestApp.backend.entity.StudentGroup;
import com.vitsed.myTestApp.backend.service.GroupService;
import com.vitsed.myTestApp.ui.MainLayout;
import com.vitsed.myTestApp.utils.names.Designation;

@Route(value = "GroupForm", layout = MainLayout.class)
@PageTitle("GroupForm")
public class GroupDialogWindow extends Dialog {

    private TextField groupNumber = new TextField(Designation.GROUP_NUMBER);
    private TextField facultyName = new TextField(Designation.FACULTY_NAME);
    private Button ok = new Button(Designation.BUTTON_OK);
    private Button cancel = new Button(Designation.BUTTON_CANCEL);
    private boolean flag;
    GroupService groupService;

    Binder<StudentGroup> binder = new Binder<>();

    public GroupDialogWindow(GroupService groupService) {
        HorizontalLayout buttonsLayout = new HorizontalLayout(ok, cancel);
        add(new FormLayout(groupNumber, facultyName, buttonsLayout));

        this.groupService = groupService;
        binder.forField(groupNumber).withConverter(new StringToIntegerConverter("Введите число")).bind(StudentGroup::getNumber, StudentGroup::setNumber);
        binder.bind(facultyName, StudentGroup::getFacultyName, StudentGroup::setFacultyName);

        setWidth("800px");
        setHeight("500px");

        add(new FormLayout(groupNumber,
                           facultyName,
                           createButtonsLayout()));
    }

    private Component createButtonsLayout() {
        ok.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        ok.addClickListener(click -> {
            if (flag) {
                groupService.save(binder.getBean());
            } else {
                groupService.delete(binder.getBean());
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
        groupNumber.clear();
        facultyName.clear();
    }

    //Events
    public void addGroup(StudentGroup newGroup, boolean flag) {
        binder.setBean(newGroup);
        this.flag = flag;
    }

}
