package com.example.HibernateAssigment;

import com.example.HibernateAssigment.Dao.AlbumDao;
import com.example.HibernateAssigment.Dao.ArtistDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class HibernateAssigmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateAssigmentApplication.class, args);
		ArtistDao artistDao = new ArtistDao();
		AlbumDao albumDao = new AlbumDao();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("1. Add Artist");
			System.out.println("2. Update Artist");
			System.out.println("3. Delete Artist");
			System.out.println("4. List All Artists");
			System.out.println("5. List Artists by Type");
			System.out.println("6. List Artists Formed After Year");
			System.out.println("7. List Albums by Artist ID");
			System.out.println("8. List Albums by Record Label");
			System.out.println("9. Exit");
			System.out.print("Choose an option: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					Artist artist = new Artist();
					System.out.print("Enter artist name: ");
					artist.setName(scanner.nextLine());
					System.out.print("Enter artist type (solo/band): ");
					artist.setType(scanner.nextLine());
					System.out.print("Enter year formed: ");
					artist.setYearFormed(scanner.nextInt());
					scanner.nextLine();
					System.out.print("Enter year split (or 0 if still active): ");
					int yearSplit = scanner.nextInt();
					scanner.nextLine();
					if (yearSplit != 0) {
						artist.setYearSplit(yearSplit);
					}
					System.out.print("Enter official site (or leave blank): ");
					String officialSite = scanner.nextLine();
					if (!officialSite.isEmpty()) {
						artist.setOfficialSite(officialSite);
					}
					artistDao.saveArtist(artist);
					break;
				case 2:
					System.out.print("Enter artist ID to update: ");
					Long updateId = scanner.nextLong();
					scanner.nextLine();
					Artist existingArtist = artistDao.gerArtistById(updateId);
					if (existingArtist != null) {
						System.out.print("Enter new artist name: ");
						existingArtist.setName(scanner.nextLine());
						System.out.print("Enter new artist type (solo/band): ");
						existingArtist.setType(scanner.nextLine());
						System.out.print("Enter new year formed: ");
						existingArtist.setYearFormed(scanner.nextInt());
						scanner.nextLine();
						System.out.print("Enter new year split (or 0 if still active): ");
						int newYearSplit = scanner.nextInt();
						scanner.nextLine();
						if (newYearSplit != 0) {
							existingArtist.setYearSplit(newYearSplit);
						} else {
							existingArtist.setYearSplit(null);
						}
						System.out.print("Enter new official site (or leave blank): ");
						String newOfficialSite = scanner.nextLine();
						if (!newOfficialSite.isEmpty()) {
							existingArtist.setOfficialSite(newOfficialSite);
						}
						artistDao.updateArtist(existingArtist);
					} else {
						System.out.println("Artist not foun.");
					}
					break;
				case 3:

					System.out.print("Enter artist ID to delete: ");
					Long deleteId = scanner.nextLong();
					scanner.nextLine();
					artistDao.deleteArtist(deleteId);
					break;
				case 4:

					List<Artist> artists = artistDao.getAllArtists();
					for (Artist art : artists) {
						System.out.println(art);
					}
					break;
				case 5:

					System.out.print("Enter artist type (solo/band): ");
					String type = scanner.nextLine();
					List<Artist> artistsByType = artistDao.getArtistsByType(type);
					for (Artist art : artistsByType) {
						System.out.println(art);
					}
					break;
				case 6:
					System.out.print("Enter year: ");
					int year = scanner.nextInt();
					scanner.nextLine();
					List<Artist> artistsAfterYear = artistDao.getAllArtistsFormedAfterYear(year);
					for (Artist art : artistsAfterYear) {
						System.out.println(art);
					}
					break;
				case 7:

					System.out.print("Enter artist ID: ");
					Long artistId = scanner.nextLong();
					scanner.nextLine();
					List<Album> albumsByArtist = albumDao.getAlbumsByArtistId(artistId);
					if (!albumsByArtist.isEmpty()) {
						Artist artistDetails = artistDao.gerArtistById(artistId);
						Discography discography = new Discography(artistDetails, albumsByArtist);
						System.out.println("Artist: " + discography.getArtist().getName());
						for (Album alb : discography.getAlbums()) {
							System.out.println("Album: " + alb.getTitle() + ", Year: " + alb.getReleaseYear() + ", Label: " + alb.getRecordLabel());
						}
					} else {
						System.out.println("No albums found for this artist.");
					}
					break;
				case 8:

					System.out.print("Enter record label: ");
					String recordLabel = scanner.nextLine();
					List<Album> albumsByLabel = albumDao.getAlbumsByRecordLabel(recordLabel);
					for (Album alb : albumsByLabel) {
						System.out.println("Artist: " + alb.getArtist().getName() + ", Album: " + alb.getTitle() + ", Year: " + alb.getReleaseYear() + ", Label: " + alb.getRecordLabel());
					}
					break;
				case 9:

					HibernateUtil.shutdown();
					scanner.close();
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}


}
