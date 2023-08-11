package build;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import anno.Column;
import anno.Insert;
import anno.PK;
import anno.Table;
import utils.Connect;

public class DAO {

  public Field pk() {
    Field[] fields = this.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(PK.class)) {
        field.setAccessible(true);
        return field;
      }
    }

    return null;
  }

  public Vector<Field> columnsSelect() {
    Vector<Field> response = new Vector<Field>();
    Field[] fields = this.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(Column.class)) {
        response.add(field);
      }
    }

    return response;
  }

  public Vector<Field> columnsInsert() {
    Vector<Field> response = new Vector<Field>();
    Field[] fields = this.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(Insert.class)) {
        response.add(field);
      }
    }

    return response;
  }

  public String tableName() {
    String response = "";
    Annotation annotation = this.getClass().getDeclaredAnnotation(Table.class);
    Method[] methods = annotation.getClass().getDeclaredMethods();
    for (Method method : methods) {
      if (method.getName().equals("value")) {
        method.setAccessible(true);
        try {
          response = String.valueOf(method.invoke(annotation));
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    }
    return response;
  }

  public String insertQuery() {
    String response = "INSERT INTO " + this.tableName() + " ";
    String cols = "(";
    String values = "(";
    Vector<Field> fields = this.columnsInsert();
    for (int i = 0; i < fields.size(); i++) {
      Field field = fields.get(i);
      field.setAccessible(true);
      try {
        cols += field.getName();
        values += "'" + field.get(this) + "'";
        if (i != fields.size() - 1) {
          cols += ", ";
          values += ", ";
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    cols += ")";
    values += ")";
    response += cols + " VALUES " + values;

    return response;
  }

  public void insert() {
    Vector<Field> fields = this.columnsInsert();
    for (int i = 0; i < fields.size(); i++) {
      Field field = fields.get(i);
      field.setAccessible(true);
      try {
        if (field.get(this) == null) {
          System.out.println(field.getName());
          return;
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

    Connection connection = Connect.connect();
    try {
      Statement statement = connection.createStatement();
      statement.execute(this.insertQuery());
      connection.commit();
      statement.close();
    } catch (Exception e) {
      try {
        connection.rollback();
      } catch (Exception f) {
        System.out.println(f.getMessage());
      }
      System.out.println(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public Object instance() {
    Object response = null;
    try {
      Constructor<?> constructor = this.getClass().getDeclaredConstructor();
      response = constructor.newInstance();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return response;
  }

  public Vector<Object> findALl() {
    Vector<Object> response = new Vector<Object>();
    Vector<Field> selectCols = this.columnsSelect();
    Connection connection = Connect.connect();
    String query = "SELECT * FROM " + this.tableName();
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while (resultSet.next()) {
        Object object = this.instance();
        for (Field field : selectCols) {
          field.setAccessible(true);
          if (field.getGenericType().getTypeName().equals("java.lang.Integer")) {
            field.set(object, resultSet.getInt(field.getName()));
          } else if (field.getGenericType().getTypeName().equals("java.lang.Double")) {
            field.set(object, resultSet.getDouble(field.getName()));
          } else if (field.getGenericType().getTypeName().equals("java.lang.String")) {
            field.set(object, resultSet.getString(field.getName()));
          } else if (field.getGenericType().getTypeName().equals("java.sql.Date")) {
            field.set(object, resultSet.getDate(field.getName()));
          }
        }
        response.add(object);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return response;
  }

  public Object findById() {
    Vector<Object> response = new Vector<Object>();
    Vector<Field> selectCols = this.columnsSelect();
    Connection connection = Connect.connect();
    String query = "";
    try {
      query = "SELECT * FROM " + this.tableName() + " WHERE " + this.pk().getName() + " = '" + this.pk().get(this)
          + "'";
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while (resultSet.next()) {
        Object object = this.instance();
        for (Field field : selectCols) {
          field.setAccessible(true);
          if (field.getGenericType().getTypeName().equals("java.lang.Integer")) {
            field.set(object, resultSet.getInt(field.getName()));
          } else if (field.getGenericType().getTypeName().equals("java.lang.Double")) {
            field.set(object, resultSet.getDouble(field.getName()));
          } else if (field.getGenericType().getTypeName().equals("java.lang.String")) {
            field.set(object, resultSet.getString(field.getName()));
          } else if (field.getGenericType().getTypeName().equals("java.sql.Date")) {
            field.set(object, resultSet.getDate(field.getName()));
          }
        }
        response.add(object);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    if (response.size() != 0) {
      return response.get(0);
    }

    return null;
  }

  public Vector<Object> findByIdMultiple() {
    Vector<Object> response = new Vector<Object>();
    Vector<Field> selectCols = this.columnsSelect();
    Connection connection = Connect.connect();
    String query = "";
    try {
      query = "SELECT * FROM " + this.tableName() + " WHERE " + this.pk().getName() + " = '" + this.pk().get(this)
          + "'";
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while (resultSet.next()) {
        Object object = this.instance();
        for (Field field : selectCols) {
          field.setAccessible(true);
          if (field.getGenericType().getTypeName().equals("java.lang.Integer")) {
            field.set(object, resultSet.getInt(field.getName()));
          } else if (field.getGenericType().getTypeName().equals("java.lang.Double")) {
            field.set(object, resultSet.getDouble(field.getName()));
          } else if (field.getGenericType().getTypeName().equals("java.lang.String")) {
            field.set(object, resultSet.getString(field.getName()));
          } else if (field.getGenericType().getTypeName().equals("java.sql.Date")) {
            field.set(object, resultSet.getDate(field.getName()));
          }
        }
        response.add(object);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return response;
  }

  public void update(String col, String value) {
    Connection connection = Connect.connect();
    try {
      String query = "UPDATE " + this.tableName() + " SET " + col + " = '" + value + "' WHERE " + this.pk().getName()
          + " = '" + this.pk().get(this) + "'";
      Statement statement = connection.createStatement();
      statement.execute(query);
      connection.commit();
      statement.close();
    } catch (Exception e) {
      try {
        connection.rollback();
      } catch (Exception f) {
        System.out.println(f.getMessage());
      }
      System.out.println(e.getMessage());
    } finally {
      try {
        connection.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

}
