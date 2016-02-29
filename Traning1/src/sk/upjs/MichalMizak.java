package sk.upjs;
  
 import java.io.*;
 import java.net.*;
 import java.nio.channels.Channels;
 import java.nio.channels.ReadableByteChannel;
 
 /**
  * mala uprava patoveho projektu, String all => StringBuilder
  * @author Ja^(tm)
  *
  */
  public class MichalMizak {
  
 	public static String savePage(final String URL) throws IOException {
 		String line = "";
 		StringBuilder all = new StringBuilder();
 		URL myUrl = null;
 		BufferedReader in = null;
 		try {
 			myUrl = new URL(URL);
 			in = new BufferedReader(new InputStreamReader(myUrl.openStream()));
 
 			while ((line = in.readLine()) != null) {
 				all.append(line + "\n");
 			}
 		} finally {
 			if (in != null) {
 				in.close();
 			}
 		}
 
 		return all.toString();
 	}
 
 	public void zapisDoHtml(String nameHtml) throws IOException {
 		try (PrintWriter zapisovac = new PrintWriter(new File(nameHtml + ".html"))) {
 			final String urlGoogle = "http://www.shmu.sk/sk/?page=1";
 
 			String obsahHtml = MichalMizak.savePage(urlGoogle);
 			zapisovac.println(obsahHtml);
 		}
 
 	}
 
 	public static void savePage2(String URL) throws IOException {
 		URL website = new URL("http://www.shmu.sk/sk/?page=1");
 		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
 		FileOutputStream fos = new FileOutputStream(URL + ".html");
 		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
 
 	}
 
 	public static void main(String[] args) throws IOException {
 
 		//// 1.moznost
 		MichalMizak a = new MichalMizak();
 		a.zapisDoHtml("allInfoMeteo");
 
 		///// 2.moznost
 		MichalMizak.savePage2("allInfoMeteo2");
 
 		// po spusteni sa vytvoria 2 subory v priecinku Training1
 
 	}
 }