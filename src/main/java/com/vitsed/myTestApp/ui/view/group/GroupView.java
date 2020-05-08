package com.vitsed.myTestApp.ui.view.group;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vitsed.myTestApp.backend.entity.StudentGroup;
import com.vitsed.myTestApp.backend.service.GroupService;
import com.vitsed.myTestApp.ui.MainLayout;
import com.vitsed.myTestApp.utils.names.Designation;

@Route(value = "groups", layout = MainLayout.class)
@PageTitle("Groups | Student App")
public class GroupView extends VerticalLayout {

    private Button add = new Button(Designation.BUTTON_ADD);
    private Button edit = new Button(Designation.BUTTON_EDIT);
    private Button delete = new Button(Designation.BUTTON_DELETE);
    private StudentGroup newGroup;
    private GroupDialogWindow groupDialog;

    private final GroupService groupService;
    private Grid<StudentGroup> grid = new Grid<>();
    public GroupView(GroupService groupService) {
        this.groupService = groupService;
        groupDialog  = new GroupDialogWindow(groupService);
        addClassName("group-view");
        setSizeFull();
        configureGrid();
        add(new H1("Список групп"), grid, createButtonsLayout());
        updateList();
    }

    private void  configureGrid() {
        grid.addClassName("group-grid");
        grid.setSizeFull();
        grid.removeAllColumns();
        grid.addColumn(StudentGroup::getNumber).setHeader("Номер группы").setSortable(true);
        grid.addColumn(StudentGroup::getFacultyName).setHeader("Факультет");

        grid.addItemClickListener(group -> newGroup = group.getItem());
    }

    private Component createButtonsLayout() {
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        edit.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        add.addClickListener(click -> {
            groupDialog.addGroup(new StudentGroup(), true);
            groupDialog.open();
        });

        edit.addClickListener(click -> {
            if(newGroup != null) {
                groupDialog.addGroup(newGroup, true);
                groupDialog.open();
            } else {
                Notification.show("Студент не выбран!");
            }
        });

        delete.addClickListener(click -> {
            if(newGroup != null) {
                groupDialog.addGroup(newGroup, false);
                groupDialog.open();
            } else {
                Notification.show("Студент не выбран!");
            }
        });

        return new HorizontalLayout(add, edit, delete);
    }



    private void updateList() {
        grid.setItems(groupService.findAll());
    }
}
