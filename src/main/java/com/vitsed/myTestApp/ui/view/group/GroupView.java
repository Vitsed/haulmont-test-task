package com.vitsed.myTestApp.ui.view.group;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
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

    private final GroupService groupService;
    private Grid<StudentGroup> grid = new Grid<>();
    public GroupView(GroupService groupService) {
        this.groupService = groupService;
        addClassName("group-view");
        setSizeFull();
        configureGrid();
        HorizontalLayout buttonsLayout = new HorizontalLayout(add, edit, delete);
        add(new H1("Список групп"), grid, buttonsLayout);
        updateList();
    }

    private void addStudentGroup() {

    }

    private void editStudentGroup() {

    }

    private void deleteStudentGroup() {

    }
    private void  configureGrid() {
        grid.addClassName("group-grid");
        grid.setSizeFull();
        grid.removeAllColumns();
        grid.addColumn(StudentGroup::getNumber).setHeader("Номер группы").setSortable(true);
        grid.addColumn(StudentGroup::getFacultyName).setHeader("Факультет");
    }

    private void updateList() {
        grid.setItems(groupService.findAll());
    }
}
