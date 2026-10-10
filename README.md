# Digital Zoo Manager

Proyek Ujian Tengah Semester (UTS) Pemrograman Berorientasi Objek (PBO) / Object-Oriented Programming (OOP) menggunakan Java & Java Swing GUI.

## 📌 Deskripsi Proyek
**Digital Zoo Manager** adalah aplikasi desktop yang memungkinkan pengguna untuk membuat dan mendaftarkan berbagai jenis hewan ke dalam kebun binatang digital yang disimpan di dalam memori (`ArrayList<Animal>`). Aplikasi ini menerapkan konsep inti OOP:
1. **Inheritance (Pewarisan):** Superclass `Animal` diwarisi oleh subclass `Mammal` dan `Bird`.
2. **Encapsulation (Enkapsulasi):** Semua atribut data bertipe `private`, dilindungi dengan getter dan setter `public`.
3. **Polymorphism (Polimorfisme):** Koleksi `ArrayList<Animal>` dapat menampung objek `Mammal` dan `Bird`, memanggil method overriding `makeSound()` dan `getInfo()`.
4. **GUI (Graphical User Interface):** Dibangun dengan Java Swing (`JFrame`, `CardLayout`, `JComboBox`, `JTextField`, `JCheckBox`, `JButton`, `JTextArea`, `JScrollPane`).

---

## 🏗️ Struktur Berkas & Hierarki Kelas

Semua kode program Java terletak di dalam folder `src/`:

```
DigitalZooManager/
├── src/
│   ├── Animal.java        # Superclass dasar (name, age, makeSound(), getInfo())
│   ├── Mammal.java        # Subclass Animal (furColor, override makeSound(), getInfo())
│   ├── Bird.java          # Subclass Animal (canFly, override makeSound(), getInfo())
│   ├── ZooFrame.java      # Tampilan GUI (View) & Logic ArrayList<Animal>
│   └── Main.java          # Entry point utama aplikasi Swing
├── uts (1).pdf            # Dokumen panduan soal UTS
├── .gitignore             # File ignore git
└── README.md              # Dokumentasi proyek
```

### Rincian Kelas
1. **`Animal.java`**
   - Atribut privat: `String name`, `int age`.
   - Constructor: `Animal(String name, int age)`.
   - Method:
     - `makeSound()`: mengembalikan `"The animal makes a sound."`
     - `getInfo()`: mengembalikan `"Name: " + name + ", Age: " + age`

2. **`Mammal.java` (extends `Animal`)**
   - Atribut privat: `String furColor`.
   - Constructor: `Mammal(String name, int age, String furColor)` memanggil `super(name, age)`.
   - Overriding:
     - `makeSound()`: mengembalikan `"The mammal makes a sound."`
     - `getInfo()`: mengembalikan `super.getInfo() + ", Fur Color: " + furColor`

3. **`Bird.java` (extends `Animal`)**
   - Atribut privat: `boolean canFly`.
   - Constructor: `Bird(String name, int age, boolean canFly)` memanggil `super(name, age)`.
   - Overriding:
     - `makeSound()`: mengembalikan `"The bird chirps."`
     - `getInfo()`: mengembalikan `super.getInfo() + ", Can Fly: " + (canFly ? "Yes" : "No")`

4. **`ZooFrame.java` (extends `JFrame`)**
   - Mengelola koleksi polimorfis `ArrayList<Animal>`.
   - Form dinamis: Saat memilih `Generic Mammal`, input `Fur Color` ditampilkan; saat memilih `Generic Bird`, checkbox `Can Fly?` ditampilkan.
   - Validasi input (nama tidak boleh kosong, usia harus berupa angka non-negatif, dsb).
   - Logging otomatis ke `JTextArea` non-editable saat hewan ditambahkan.
   - Tombol pendukung: `Show All Animals` (rekap seluruh hewan dalam zoo) dan `Clear Log`.

5. **`Main.java`**
   - Menjalankan `ZooFrame` di Event Dispatch Thread (`SwingUtilities.invokeLater`).

---

## 🚀 Cara Menjalankan Aplikasi

### Menggunakan Terminal / Command Prompt / PowerShell:
1. Pastikan berada di folder `DigitalZooManager`:
   ```bash
   cd c:\javaproject\DigitalZooManager
   ```
2. Kompilasi semua file Java:
   ```bash
   javac -d bin src/*.java
   ```
3. Jalankan aplikasi:
   ```bash
   java -cp bin Main
   ```

### Menggunakan IDE (IntelliJ IDEA / VS Code / Eclipse):
- Buka folder `DigitalZooManager` sebagai project Java.
- Pastikan folder `src` ditandai sebagai **Sources Root**.
- Jalankan file `Main.java` (`Run 'Main.main()'`).
