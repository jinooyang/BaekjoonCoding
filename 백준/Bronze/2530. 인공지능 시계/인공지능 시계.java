import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());

		// 시간을 초로 변환
		int totalTimeInSeconds = h * 3600 + m * 60 + s;

		// 초를 더함
		totalTimeInSeconds += t;

		// 초를 다시 시, 분, 초로 분해
		int newH = totalTimeInSeconds / 3600;
		int newM = (totalTimeInSeconds % 3600) / 60;
		int newS = totalTimeInSeconds % 60;
		while (newH >= 24)
			newH -= 24;
		System.out.println(newH + " " + newM + " " + newS);
	}
}