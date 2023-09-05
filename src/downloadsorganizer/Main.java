package downloadsorganizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Main {

	// Absolute file path for the downloads folder
	private static final String downloadsFilePath = "C:\\Users\\userName\\Downloads";

	// Defining the different file formats
	private static final String[] audioFormats = { ".wav", ".mp3", ".wma" };
	private static final String[] imageFormats = { ".jpg", ".jpeg", ".png", ".gif" };
	private static final String[] documentFormats = { ".txt", ".pdf", ".doc", ".docx", ".ppt", ".pptx" };
	private static final String[] videoFormats = { "mp4", "mov", "wmv", ".avi" };

	// Defining the different types of folders
	private static final File downloadsFolder = new File(downloadsFilePath);
	private static final File audioFolder = new File(downloadsFilePath + "\\Audio");
	private static final File imagesFolder = new File(downloadsFilePath + "\\Images");
	private static final File documentsFolder = new File(downloadsFilePath + "\\Documents");
	private static final File videosFolder = new File(downloadsFilePath + "\\Videos");
	private static final File othersFolder = new File(downloadsFilePath + "\\Others");

	public static void main(String[] args) {

		// Creating the appropriate folders if they don't already exist
		if (!audioFolder.exists()) {
			audioFolder.mkdir();
		}
		if (!imagesFolder.exists()) {
			imagesFolder.mkdir();
		}
		if (!documentsFolder.exists()) {
			documentsFolder.mkdir();
		}
		if (!videosFolder.exists()) {
			videosFolder.mkdir();
		}
		if (!othersFolder.exists()) {
			othersFolder.mkdir();
		}

		// Moving all of the files within the downloads folder into the folder
		// corresponding to the file type
		File[] files = downloadsFolder.listFiles();
		for (File currentFile : files) {
			if (!currentFile.isHidden() && !currentFile.isDirectory()) {
				moveFileToCorrectFolder(currentFile);
			}
		}

	}

	/**
	 * Moves the passed file to the appropriate folder.
	 * 
	 * @param file The file that is to be moved.
	 */
	private static void moveFileToCorrectFolder(File file) {

		String filename = file.getName();
		String filenameExtension = filename.substring(filename.indexOf('.'));

		// Checking if the filename extension matches one of the previously defined file
		// formats
		for (String current : audioFormats) {
			if (current.equals(filenameExtension)) {
				try {
					Files.move(file.toPath(), audioFolder.toPath().resolve(file.getName()),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		for (String format : imageFormats) {
			if (format.equals(filenameExtension)) {
				try {
					Files.move(file.toPath(), imagesFolder.toPath().resolve(file.getName()),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		for (String format : documentFormats) {
			if (format.equals(filenameExtension)) {
				try {
					Files.move(file.toPath(), documentsFolder.toPath().resolve(file.getName()),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		for (String format : videoFormats) {
			if (format.equals(filenameExtension)) {
				try {
					Files.move(file.toPath(), videosFolder.toPath().resolve(file.getName()),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		// If the filename extension does not match one of the previously defined file
		// formats, the file will be moved to the "others" folder
		try {
			Files.move(file.toPath(), othersFolder.toPath().resolve(file.getName()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
