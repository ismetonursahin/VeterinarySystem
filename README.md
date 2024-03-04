# Veteriner Yönetim Sistemi

Veteriner Yönetim Sistemi, hayvan sahipleri, hayvanlar, veteriner hekimler, randevular ve aşılar gibi veterinerlikle ilgili temel kayıtları yönetmek için geliştirilmiş bir REST API projesidir. Bu uygulama veteriner klinikleri tarafından kullanılabilir.

## Teknolojiler

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Lombok
- Mapstruct
- Postman
- Swagger

## Proje Yapısı

### ENTITY Tablosu:

| Entity Adı | Açıklama                          |
|------------|-----------------------------------|
| Vaccine    | Aşı bilgilerini tutar             |
| Doctor     | Veteriner hekim bilgilerini tutar |
| Customer   | Hayvan sahibi bilgilerini tutar  |
| AvailableDate | Müsait gün bilgilerini tutar   |
| Appointment | Randevu bilgilerini tutar        |

### REPOSITORY Tablosu:

| Repository Adı | Açıklama                                   |
|----------------|--------------------------------------------|
| VaccineRepository | Aşılarla ilgili veritabanı işlemleri için arayüz |
| DoctorRepository  | Veteriner hekimleriyle ilgili veritabanı işlemleri için arayüz |
| CustomerRepository | Hayvan sahipleriyle ilgili veritabanı işlemleri için arayüz |
| AvailableDateRepository | Müsait günlerle ilgili veritabanı işlemleri için arayüz |
| AppointmentRepository | Randevularla ilgili veritabanı işlemleri için arayüz |

### DTO / REQUEST-RESPONSE Tablosu:

| DTO / Request-Response Adı | Açıklama                                    |
|----------------------------|---------------------------------------------|
| VaccineDTO                 | Aşı bilgilerini taşır                       |
| DoctorDTO                  | Veteriner hekim bilgilerini taşır           |
| CustomerDTO                | Hayvan sahibi bilgilerini taşır            |
| AvailableDateDTO           | Müsait gün bilgilerini taşır               |
| AppointmentDTO             | Randevu bilgilerini taşır                  |

### MAPPER Tablosu:

| Mapper Adı | Açıklama                                      |
|------------|-----------------------------------------------|
| VaccineMapper | Entity ve DTO arasında aşı bilgisi dönüştürme işlemleri için kullanılır |
| DoctorMapper  | Entity ve DTO arasında veteriner hekim bilgisi dönüştürme işlemleri için kullanılır |
| CustomerMapper | Entity ve DTO arasında hayvan sahibi bilgisi dönüştürme işlemleri için kullanılır |
| AvailableDateMapper | Entity ve DTO arasında müsait gün bilgisi dönüştürme işlemleri için kullanılır |
| AppointmentMapper | Entity ve DTO arasında randevu bilgisi dönüştürme işlemleri için kullanılır |

### SERVICE Tablosu:

| Service Adı | Açıklama                                  |
|-------------|-------------------------------------------|
| VaccineService | Aşılarla ilgili iş katmanı işlemleri için kullanılır |
| DoctorService  | Veteriner hekimleriyle ilgili iş katmanı işlemleri için kullanılır |
| CustomerService | Hayvan sahipleriyle ilgili iş katmanı işlemleri için kullanılır |
| AvailableDateService | Müsait günlerle ilgili iş katmanı işlemleri için kullanılır |
| AppointmentService | Randevularla ilgili iş katmanı işlemleri için kullanılır |

### CONTROLLER Tablosu:

| Controller Adı | Açıklama                                      |
|----------------|-----------------------------------------------|
| VaccineController | Aşılarla ilgili API endpoint'lerini temsil eder |
| DoctorController  | Veteriner hekimleriyle ilgili API endpoint'lerini temsil eder |
| CustomerController | Hayvan sahipleriyle ilgili API endpoint'lerini temsil eder |
| AvailableDateController | Müsait günlerle ilgili API endpoint'lerini temsil eder |
| AppointmentController | Randevularla ilgili API endpoint'lerini temsil eder |

## Uygulamayı Başlatma

Proje Spring Boot ile geliştirildiği için, uygulamayı ayağa kaldırmak için aşağıdaki adımları izleyebilirsiniz:

1. Proje kaynak kodlarını bilgisayarınıza indirin.
2. PostgreSQL veritabanınızı oluşturun ve bağlantı bilgilerini `application.properties` dosyasında güncelleyin.
3. Projeyi bir IDE'de (IntelliJ IDEA, Eclipse vb.) açın.
4. `VeterinaryApp` sınıfını bulun ve çalıştırın.
5. Uygulama başlatıldığında, [http://localhost:8080]([http://localhost:8080](http://localhost:8080/swagger-ui/index.html#/)) adresinden API'ye erişebilirsiniz.

## API Endpoints

Aşağıda, API'nin sunduğu temel endpoint'lerin bir listesi bulunmaktadır:

| Endpoint                                | HTTP Metodu | Açıklama                                    |
|-----------------------------------------|-------------|---------------------------------------------|
| /api/v1/vaccines/{id}                   | GET         | Belirtilen ID'ye sahip aşıyı getir        |
| /api/v1/vaccines/{id}                   | PUT         | Belirtilen ID'ye sahip aşıyı güncelle     |
| /api/v1/vaccines/{id}                   | DELETE      | Belirtilen ID'ye sahip aşıyı sil          |
| /api/v1/vaccines                        | GET         | Tüm aşıları getir                          |
| /api/v1/vaccines                        | POST        | Aşı ekle                                    |
| /api/v1/vaccines/finishDate             | GET         | Girilen tarih aralığına göre aşı kayıtları |
| /api/v1/vaccines/findByAnimalId/{animalId} | GET      | Belirli bir hayvana ait tüm aşı kayıtları  |

| Endpoint                                | HTTP Metodu | Açıklama                                    |
|-----------------------------------------|-------------|---------------------------------------------|
| /api/v1/doctors/{id}                    | GET         | Belirtilen ID'ye sahip veteriner hekimini getir |
| /api/v1/doctors/{id}                    | PUT         | Belirtilen ID'ye sahip veteriner hekimini güncelle |
| /api/v1/doctors/{id}                    | DELETE      | Belirtilen ID'ye sahip veteriner hekimini sil |
| /api/v1/doctors                         | GET         | Tüm veteriner hekimlerini getir            |
| /api/v1/doctors                         | POST        | Veteriner hekimi ekle                      |
