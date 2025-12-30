# 🔐 Collatz Conjecture Based Key Generation System

Bu proje, **Collatz Sanısı (3n + 1)** matematiksel modelini kullanarak **deterministik ancak yüksek karmaşıklığa sahip 64-bit kriptografik anahtarlar** üretmek amacıyla geliştirilmiştir. 🚀  
Algoritma, rastgelelik ihtiyacını Collatz döngüsünün **kaotik doğasından** alır.

---

## 📌 Proje Hakkında

Standart Collatz döngüsünde bulunan **tahmin edilebilirlik** ve **sonsuz döngüye girme** problemlerini aşmak amacıyla sisteme iki temel güvenlik katmanı eklenmiştir:

### 🔄 Dinamik Yeniden Tohumlama (Dynamic Re-seeding)
Sayı **1** değerine ulaştığında algoritma durmaz. Başlangıç tohumu (**seed**) 1 artırılır ve döngü yeni bir kurguyla devam eder.

### ✂️ Bit Kırpma (Truncation)
Hedef uzunluk **64 bit** olmasına rağmen algoritma **68 bit** üretir. Son **4 bit**, algoritmanın izlerini gizlemek ve tersine mühendisliği zorlaştırmak amacıyla atılır.

---

## 📊 Algoritma Akış Şeması

Aşağıdaki diyagram, sistemin bir başlangıç sayısından (**seed**) başlayarak nasıl **güvenli bir Hex anahtara** dönüştüğünü özetler.

> ⚠️ Not: `flowchart.png` dosyasını bu dizine eklemeyi unutmayın.

![Collatz Key Generation Flowchart](flowchart.png)

---

## 🛠 Çalışma Mantığı

Algoritma aşağıdaki adımları izler:

### 1️⃣ Giriş
Kullanıcıdan `long` tipinde bir başlangıç değeri (**Seed**) alınır.

### 2️⃣ Üretim (Generation)
- Sayı **çift** ise:  
  `n = n / 2` → Bit: **0**
- Sayı **tek** ise:  
  `n = 3n + 1` → Bit: **1**

### 3️⃣ Döngü Kırma
Sayı **1** değerine ulaşırsa:
- Seed değeri **1 artırılır**
- Yeni sayı bu değerden devam eder

### 4️⃣ Kısıtlama ve Kırpma
- Toplam **68 bit** üretilir
- Son **4 bit**, `4-2-1` döngüsüne girme ihtimali yüksek olduğu için sistemden atılır

### 5️⃣ Çıktı
Kalan **64 bit**, **Hexadecimal (16’lık)** sayı sistemine dönüştürülerek kullanıcıya sunulur.

---

## 💻 Kullanım Talimatları

### Gereksinimler
- **Java JDK 8** veya üzeri
- Terminal veya herhangi bir Java IDE (VS Code, IntelliJ IDEA, Eclipse)

### Çalıştırma

```bash
javac CollatzCipher.java
java CollatzCipher

## 📋 Menü Seçenekleri

### Seçenek 1
Kullanıcıdan alınan bir başlangıç sayısı (**seed**) kullanılarak, Collatz tabanlı algoritma ile **64-bit uzunluğunda, kullanıcıya özgü kriptografik bir anahtar** üretilir.

### Seçenek 2
Kullanıcının elinde bulunan bir **Hexadecimal (16’lık) anahtarın**, hangi başlangıç değeri (**seed**) ile üretildiğini tespit etmek amacıyla **Brute Force** yöntemi uygulanır.  
Bu işlem sırasında **ilk 1.000.000** olası seed değeri sırasıyla taranır.
