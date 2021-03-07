package com.miit;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientTest {
	public static void main(String[] args) {
		String sql = "INSERT INTO storetextfile_table(file_name,file_size_in_kb,file_extension,file_content) values(?,?,?,?)";
		Path dir = Paths.get("C:\\Users\\yashr\\eclipse-workspace\\CLOB\\src\\main\\resources\\inputfiles");

		try (Stream<Path> list = Files.list(dir);
				Connection connection = DB.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			
			List<Path> pathList = list.collect(Collectors.toList());
			
			for(Path path : pathList) {
				System.out.println(path.getFileName());
				File file = path.toFile();
				String fileName = file.getName();
				long fileLength = file.length();
				System.out.println(fileLength);
				long fileLengthInKb = fileLength / 1024;
				
				ps.setString(1, fileName);
				ps.setLong(2,fileLengthInKb);
				
				ps.setString(3, fileName.substring(fileName.lastIndexOf(".") + 1));
				ps.setCharacterStream(4, new FileReader(file));
				
				ps.addBatch();
			}
			
			int[] execBatch = ps.executeBatch();
			for(int i : execBatch) {
				System.out.println(i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
