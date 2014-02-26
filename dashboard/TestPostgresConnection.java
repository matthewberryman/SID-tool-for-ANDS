package dashboard;

public class TestPostgresConnection {

	public static void main(String[] argv) {

		
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		String url = "jdbc:postgresql://localhost:5432/testdb";
		String user = "junm";
		String password = "ccpitgao";
		
		DBReader reader = new DBReader(url, user, password);
//		String query = "select count(*) db_count from pg_catalog.pg_database where datname = 'testdb';";
		String query = "COPY test_1 to '/tmp/test_1.csv' with csv header;";
		reader.executeUpdate(query);
//		ResultSet rs = reader.executeQuery(query);
		
//		try {
//			while (rs.next()) {
//				System.out.println(Integer.toString(rs.getInt("db_count")));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println("PostgreSQL JDBC Driver Registered!");

//		Connection connection = null;
//
//		try {
//
//			connection = DriverManager.getConnection(
//					"jdbc:postgresql://localhost:5432/postgres", "junm",
//					"ccpitgao");
//
//		} catch (SQLException e) {
//
//			System.out.println("Connection Failed! Check output console");
//			e.printStackTrace();
//			return;
//
//		}

//		if (connection != null) {
//			try {
//				Statement statement = connection.createStatement();
//
//				ResultSet rs = null;
//				rs = statement
//						.executeQuery("select count(*) db_count from pg_catalog.pg_database where datname = 'testdb';");
//				Boolean dbExist = false;
//				while (rs.next()) {
//					if (rs.getInt("db_count") > 0) {
//						dbExist = true;
//					}
//				}
//
//				if (dbExist) {
//				} else {
//					System.out.println("db: testdb does not exist!");
//					statement.executeUpdate("create database testdb");
//				}
//				connection.close();
//				try {
//					Class.forName("org.postgresql.Driver");
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

//				connection = DriverManager.getConnection(
//						"jdbc:postgresql://localhost:5432/testdb", "junm",
//						"ccpitgao");
//				if (connection != null) {
//					System.out.println("Connection to "
//							+ connection.getCatalog() + " is built!");
//					statement = connection.createStatement();
//					statement
//							.executeUpdate("create table test_1 (id integer, table_id integer, cell_id text);");
//					statement
//							.executeUpdate("insert into test_1 values (1,1,'b1'), (1,2,'b2');");
//					statement.close();
//				}
//				connection.close();
//
//				System.out.println("Connection to testdb is closed.");
				// ResultSet rs =
				// statement.executeQuery("select * from agg_household_income");
				// // household_id
				// // individual_id
				// // average_income
				// System.out.println("household_id" +
				// "\t" + "individual_id" +
				// "\t" + "average_income");
				// while (rs.next()) {
				// System.out.println(Integer.toString(rs.getInt("household_id"))
				// +
				// "\t" + Integer.toString(rs.getInt("individual_id")) +
				// "\t" + Double.toString(rs.getDouble("average_income")));
				// }
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			System.out.println("You made it, take control your database now!");
//
//		} else {
//			System.out.println("Failed to make connection!");
//		}
	}

}