# Digital Zoo Manager

Aplikasi desktop sederhana berbasis Java Swing untuk mengelola data kebun binatang secara in-memory. Proyek ini dibuat untuk memenuhi tugas Ujian Tengah Semester (UTS) mata kuliah Pemrograman Berorientasi Objek.

## Fitur & Implementasi

- **Pewarisan & Polimorfisme (OOP)**:
  - Base class `Animal` dengan atribut nama dan umur.
  - Subclass `Mammal` dengan atribut khusus `furColor` serta override suara dan info.
  - Subclass `Bird` dengan atribut khusus `canFly` serta override suara dan info.
  - Koleksi `ArrayList<Animal>` untuk menyimpan berbagai objek hewan secara polimorfis.
- **Antarmuka Pengguna (Swing GUI)**:
  - Form input yang otomatis menyesuaikan field (input warna bulu untuk mamalia, checkbox terbang untuk burung).
  - Validasi input (nama wajib diisi, umur harus berupa angka valid).
  - Area log untuk menampilkan suara dan informasi hewan yang baru saja ditambahkan.
  - Tombol untuk melihat rekap seluruh hewan yang ada di kebun binatang.

## Struktur Folder

```
DigitalZooManager/
├── src/
│   ├── Animal.java       # Base class hewan
│   ├── Mammal.java       # Subclass mamalia
│   ├── Bird.java         # Subclass burung
│   ├── ZooFrame.java     # Form GUI dan logika aplikasi
│   └── Main.java         # Entry point (menjalankan GUI)
├── .gitignore
└── README.md
```

## Cara Menjalankan

### Menggunakan Terminal
1. Kompilasi kode sumber:
   ```bash
   javac -d bin src/*.java
   ```
2. Jalankan aplikasi:
   ```bash
   java -cp bin Main
   ```

### Menggunakan IDE (IntelliJ IDEA / VS Code)
1. Buka folder `DigitalZooManager` pada IDE.
2. Pastikan folder `src` dikenali sebagai *source folder*.
3. Jalankan file `src/Main.java`.
