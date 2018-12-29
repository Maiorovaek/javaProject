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


/**
 * Entry point classes define <code>onModuleLoad()</code>
 */


public class MySampleApplication implements EntryPoint {

    //вызов сервлета на стороне клиента: создание объекта сервиса
    //final - кэширование объекта сервиса для предотвращения его повторного создания при многократных вызовах сервиса
    private BookssServiceAsync booksService = GWT.create(BookssService.class);
    private static CellTable<Bookss> table;
    private static ArrayList<Bookss> books = new ArrayList<Bookss>();

    Bookss clickBook = null;
    TextBox idTextBox = new TextBox();
    TextBox authorTextBox = new TextBox();
    TextBox nameBookTextBox = new TextBox();
    TextBox numberPageTextBox = new TextBox();
    TextBox yearTextBox = new TextBox();
    Button btnAdd = new Button("Add book");
    Button btnDelete = new Button("Delete book");
    Button button = new Button("Next page");
    Button buttonprev = new Button("Previous page");


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


        dataProvider.refresh();


        pageColumn.setSortable(true);
        idColumn.setSortable(true);
        ColumnSortEvent.ListHandler<Bookss> columnSortHandler = new ColumnSortEvent.ListHandler<>(list);
        columnSortHandler.setComparator(pageColumn, new Comparator<Bookss>() {
            @Override
            public int compare(Bookss o1, Bookss o2) {
                if (o1 == o2) {
                    return 0;
                }
                if (o1 != null) {
                    return (o2 != null) ? new Integer(o1.getNumberPages()).compareTo(o2.getNumberPages()) : 1;

                }
                return -1;
            }
        });
        table.addColumnSortHandler(columnSortHandler);


        table.setRowData(books);
        table.setPageSize(10);

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                int start = table.getVisibleRange().getStart();
                int pageSize = table.getPageSize();
                start = start + pageSize;
                table.setRowData(books);
                table.setVisibleRange(start, pageSize);
            }
        });

        buttonprev.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                int end = table.getVisibleRange().getLength();
                int pageSize = table.getPageSize();
                end = end - pageSize;
                table.setRowData(books);
                table.setVisibleRange(end, pageSize);
            }
        });

    }

    public void createPanel() {
        idTextBox.setText("id");
        authorTextBox.setText("Author");
        nameBookTextBox.setText("Name book");
        numberPageTextBox.setText("Number of page");
        yearTextBox.setText("Year of publication");

        final RootPanel panel = RootPanel.get("container");
        panel.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
        panel.add(idTextBox);
        panel.add(authorTextBox);
        panel.add(nameBookTextBox);
        panel.add(numberPageTextBox);
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
        panel.add(buttonprev);
        panel.add(button);

        panel.add(btnDelete);


    }

    private static final List<String> Items = new ArrayList<>();

    //автоматически вызывается при загрузке GWT-модуля
    public void onModuleLoad() {
        booksService.listBooks(new MessageCallBack());


        btnAdd.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (vlidate() == true) {
                    // Window.alert("Book added successfully.");
                    Integer idField = Integer.parseInt(idTextBox.getText());
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

                        }
                    });
                } else {
                    Window.alert("Please, enter filL");

                }
            }
        });


        btnDelete.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {


                int row = table.getKeyboardSelectedRow();
                table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
               // table.setSelectionModel(singleSelectionModel);

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


                  //  table.setSelectionModel(singleSelectionModel);
//                pane.add(table);
//
//                RootPanel.get("bookList").add(mainPanel);
                    Window.alert(clickBook + " delete!!!!!!!!!!!");
                } else {
                    Window.alert("select row in table");
                }
            }

        });



    }


    private boolean vlidate() {
        boolean result = true;

        String message = "Please, enter all Field";
        if (authorTextBox.getText().isEmpty()) {
            authorTextBox.setStyleName("field_error");
            result = false;
        }
        if (nameBookTextBox.getText().isEmpty()) {
            nameBookTextBox.setStyleName("field_error");
            result = false;
        }
        if (numberPageTextBox.getText().isEmpty()) {
            numberPageTextBox.setStyleName("field_error");
            message = "Please, enter a number pages. Example, 200\n";
            result = false;
        }
        if (yearTextBox.getText().isEmpty()) {
            yearTextBox.setStyleName("field_error");
            message = "Please, enter  year. Example, 2011\n";
            result = false;
        }
        if (result == false) {
            Window.alert(message);
        }

        return result;
    }


}