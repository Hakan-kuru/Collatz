import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String currentKey = ""; 

        while (true) {
            System.out.println("\n--- Collatz 64-Bit Sistemi ---");
            System.out.println("1 - Anahtar Üret (Seed -> 64-Bit Hex)");
            System.out.println("2 - Seed'i Geri Bul (Hex -> Seed)");
            System.out.println("q - Çıkış");
            System.out.print("Seçiminiz: ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("q")) break;

            if (choice.equals("1")) {
                System.out.print("Başlangıç değerini (Seed) girin: ");
                try {
                    long seed = Long.parseLong(scanner.nextLine());
                    currentKey = generateCollatzKey(seed, 64);
                    System.out.println("\n[SİSTEM] Üretilen 64-Bit Anahtar (Hex): " + currentKey);
                } catch (Exception e) {
                    System.out.println("Hata: Geçerli bir sayı giriniz.");
                }

            } else if (choice.equals("2")) {
                System.out.print("Çözülecek Hex anahtarı girin: ");
                String hexToFind = scanner.nextLine().toUpperCase();
                
                System.out.println("Seed aranıyor... (İlk 1 milyon sayı taranıyor)");
                long foundSeed = findSeedFromKey(hexToFind, 64);
                
                if (foundSeed != -1) {
                    System.out.println("\n[BULDUM!] Bu anahtarı üreten seed: " + foundSeed);
                } else {
                    System.out.println("\n[HATA] İlk 1.000.000 sayı içinde eşleşme bulunamadı.");
                }
            }
        }
        scanner.close();
    }

    // ANAHTAR ÜRETME: 64 bit üretmek için 68 bit işlem yapıp son 4'ü atar
    public static String generateCollatzKey(long seed, int n) {
        int totalNeeded = n + 4; // 68 bit üretim
        StringBuilder binaryString = new StringBuilder();
        long currentSeed = seed;
        long currentNum = seed;

        while (binaryString.length() < totalNeeded) {
            if (currentNum % 2 == 0) {
                currentNum /= 2;
                binaryString.append("0");
            } else {
                currentNum = (3 * currentNum) + 1;
                binaryString.append("1");
            }

            // 1'e ulaşıldığında seed'i bir artırıp tekrar fırlat (Döngü Kırıcı)
            if (currentNum == 1) {
                currentSeed++;
                currentNum = currentSeed;
            }
        }

        // İlk 64 biti alıyoruz (Kırpma)
        String truncatedBinary = binaryString.substring(0, n);
        
        // 64 bit veriyi Hex'e çevirirken 'parseUnsignedLong' kullanmalıyız
        // Çünkü 64. bit işaret biti (negative) olarak algılanabilir.
        java.math.BigInteger decimalValue = new java.math.BigInteger(truncatedBinary, 2);
        return decimalValue.toString(16).toUpperCase();
    }

    // SEED BULMA: Deneme-Yanılma (Brute Force)
    public static long findSeedFromKey(String targetHex, int n) {
        for (long testSeed = 1; testSeed < 1000000; testSeed++) {
            String testKey = generateCollatzKey(testSeed, n);
            if (testKey.equals(targetHex)) {
                return testSeed;
            }
        }
        return -1;
    }
}