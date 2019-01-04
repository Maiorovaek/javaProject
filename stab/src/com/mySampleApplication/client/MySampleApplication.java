package com.mySampleApplication.client;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.*;


public class MySampleApplication implements EntryPoint {

    //вызов сервлета на стороне клиента: создание объекта сервиса
    //final - кэширование объекта сервиса для предотвращения его повторного создания при многократных вызовах сервиса
    private BookssServiceAsync booksService = GWT.create(BookssService.class);
    private static CellTable<Bookss> table;
    private static ArrayList<Bookss> books = new ArrayList<Bookss>();
    private Button sortButton = new Button("Sort by author");
    Bookss clickBook = null;
    //    TextBox idTextBox = new TextBox();
    TextBox authorTextBox = new TextBox();
    TextBox nameBookTextBox = new TextBox();
    TextBox numberPageTextBox = new TextBox();
    TextBox yearTextBox = new TextBox();
    Button btnAdd = new Button("Add book");
    Button btnDelete = new Button("Delete book");

    Label authorLabel = new Label("Author");
    Label nameBookLabel = new Label("Name book");
    Label numberPageLabel = new Label("Number of page");
    Label yearofpublication = new Label("Year of publication");

    //Вызов методов сервиса с обработкой результатов в объекте AsyncCallback<Bookss>
    private class MessageCallBack implements AsyncCallback<ArrayList<Bookss>> {
        //автоматически вызывается метод в случае возникновения ошибки взаимодействия с  сервером
        @Override
        public void onFailure(Throwable caught) {
            Window.alert("Unable to obtain server response: "
                    + caught.getMessage());
        }

        @Override
        public void onSuccess(ArrayList<Bookss> result) {
            books = result;
            createTableNew(books);
            createPanel();
        }
    }


    private void createTableNew(ArrayList<Bookss> books) {
        table = new CellTable<Bookss>();


        NumberCell idCell = new NumberCell();
        Column<Bookss, Number> idColumn = new Column<Bookss, Number>(idCell) {
            @Override
            public Number getValue(Bookss object) {
                return object.getId();
            }
        };

        TextColumn<Bookss> strAuthor = new TextColumn<Bookss>() {
            @Override
            public String getValue(Bookss object) {
                return object.getAuthor();
            }
        };

        TextColumn<Bookss> nameBook = new TextColumn<Bookss>() {
            @Override
            public String getValue(Bookss object) {
                return object.getNameBook();
            }
        };


        NumberCell pageCell = new NumberCell();
        Column<Bookss, Number> pageColumn = new Column<Bookss, Number>(pageCell) {

            @Override
            public Number getValue(Bookss object) {
                return object.getNumberPages();
            }
        };


        NumberCell yearCell = new NumberCell();
        Column<Bookss, Number> yearColumn = new Column<Bookss, Number>(yearCell) {

            @Override
            public Number getValue(Bookss object) {
                return object.getYear();
            }
        };


        DateCell dateCell = new DateCell();
        Column<Bookss, Date> dateColumn = new Column<Bookss, Date>(dateCell) {
            @Override
            public Date getValue(Bookss object) {
                return object.getDateCreate();
            }
        };


        table.addColumn(idColumn, "id");
        table.addColumn(strAuthor, "Author");
        table.addColumn(nameBook, "Name Book");
        table.addColumn(pageColumn, "Page");
        table.addColumn(yearColumn, "Year");
        table.addColumn(dateColumn, "Date add");

        ListDataProvider<Bookss> dataProvider = new ListDataProvider<>();
        dataProvider.addDataDisplay(table);
        final List<Bookss> list = dataProvider.getList();
        for (Bookss data : books) {
            list.add(data);
        }


       // dataProvider.refresh();


        table.setRowData(books);

    }

    public void createPanel() {


        final RootPanel panel = RootPanel.get("container");
        panel.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);

        panel.add(authorLabel);
        panel.add(authorTextBox);
        panel.add(nameBookLabel);
        panel.add(nameBookTextBox);
        panel.add(numberPageLabel);
        panel.add(numberPageTextBox);
        panel.add(yearofpublication);
        panel.add(yearTextBox);
        final SingleSelectionModel<Bookss> singleSelectionModel = new SingleSelectionModel<Bookss>();


        SelectionChangeEvent.Handler tableHandler = new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                Bookss selectedBook = singleSelectionModel.getSelectedObject();


                Integer idDel = selectedBook.getId();
                String authorDel = selectedBook.getAuthor();
                String nameBookDel = selectedBook.getNameBook();
                Integer pageDel = selectedBook.getNumberPages();
                Integer yearDel = selectedBook.getYear();
                Date dateDel = selectedBook.getDateCreate();

                selectedBook = new Bookss(idDel, authorDel, nameBookDel, pageDel, yearDel, dateDel);
                clickBook = selectedBook;
            }
        };

        singleSelectionModel.addSelectionChangeHandler(tableHandler);
        table.setSelectionModel(singleSelectionModel);

        panel.add(table);
        panel.add(btnAdd);
        panel.add(sortButton);

        panel.add(btnDelete);


    }


    //автоматически вызывается при загрузке GWT-модуля
    public void onModuleLoad() {
        booksService.listBooks(new MessageCallBack());


        btnAdd.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {


                setDefaultFieldStyle();
                    Integer idField = Integer.valueOf(String.valueOf(books.size() + 1));
                if (validate()) {
                    String authorField = authorTextBox.getText();
                    String nameBookField = nameBookTextBox.getText();
                    Integer numberPageFiled = Integer.parseInt(numberPageTextBox.getText());
                    Integer yearField = Integer.parseInt(yearTextBox.getText());


                    final Bookss newBook1 = new Bookss(idField, authorField, nameBookField, numberPageFiled, yearField, new Date());

                    AddBookServiceAsync addBookService = GWT.create(AddBookService.class);

                    addBookService.addBook(newBook1, new AsyncCallback() {
                        @Override
                        public void onFailure(Throwable caught) {
                            Window.alert("Unable to obtain server response: "
                                    + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(Object result) {
                                books.add(newBook1);
                                table.removeFromParent();
                                createTableNew(books);
                                createPanel();
                                Window.alert("you added a book " + newBook1.getNameBook() + "!");
                                authorTextBox.setText("");
                                nameBookTextBox.setText("");
                                numberPageTextBox.setText("");
                                yearTextBox.setText("");

                        }
                    });
                }

            }
        });


        btnDelete.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                int row = table.getKeyboardSelectedRow();
                table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
                DeleteBookServiceAsync deleteBookServiceAsync = GWT.create(DeleteBookService.class);
                if (clickBook != null) {
                    deleteBookServiceAsync.deleteBook(clickBook, new AsyncCallback() {
                        @Override
                        public void onFailure(Throwable caught) {

                            Window.alert("DelBook to obtain server response: "
                                    + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(Object result) {
                            books.remove(row);
                            table.setRowData(row, books);
                            table.removeFromParent();
                            createTableNew(books);
                            createPanel();
                        }
                    });


                    Window.alert("You deleted the book" + clickBook.getNameBook() + "!");
                } else {
                    Window.alert("select row in table");
                }
            }

        });


        sortButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                SortedServiceAsync sortBookService = GWT.create(SortedService.class);

                sortBookService.sortedBook(books, new AsyncCallback<ArrayList<Bookss>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(ArrayList<Bookss> result) {
                        books = result;
                        table.removeFromParent();
                        createTableNew(books);
                        createPanel();


                    }
                });

            }
        });


    }


    private boolean isNumber(String str) {
        if (str.matches("[0-9]*")) {
            return true;
        }
        return false;
    }




    private boolean validate() {
        boolean result = true;
        String alert = "Please fill fields. \n";
        if(authorTextBox.getText().isEmpty()) {
            authorTextBox.setStyleName("status-error");
            result = false;
        }
        if(nameBookTextBox.getText().isEmpty()) {
            nameBookTextBox.setStyleName("status-error");
            result = false;
        }
        if(numberPageTextBox.getText().isEmpty()||!isNumber(numberPageTextBox.getText())) {
            numberPageTextBox.setStyleName("status-error");
            alert += "Please, enter a number at Pages Field.\n";
            result = false;
        }
        if(yearTextBox.getText().isEmpty() ||  !isNumber(yearTextBox.getText()) || Integer.valueOf(yearTextBox.getText()) > 2019 ) {
            yearTextBox.setStyleName("status-error");
            alert += "Please, enter a number at Year Field.\n";
            result = false;
        }
        if(result == false) {
            Window.alert(alert);
        }

        return result;

    }
    private void setDefaultFieldStyle() {
        authorTextBox.setStyleName("status-ok");
        nameBookTextBox.setStyleName("status-ok");
        numberPageTextBox.setStyleName("status-ok");
        yearTextBox.setStyleName("status-ok");
    }

}