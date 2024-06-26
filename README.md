# Veteriner Yönetim Sistemi

Veteriner Yönetim Sistemi, hayvan sahipleri, hayvanlar, veteriner hekimler, randevular ve aşılar gibi veterinerlikle ilgili temel kayıtları yönetmek için geliştirilmiş bir REST API projesidir. 
------
![](veterinary_app/img/uml.png)
----
## Teknolojiler

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Lombok
- ModelMapper
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
| Animal    | Hayvan bilgilerini tutar        |

### REPOSITORY Tablosu:

| Repository Adı | Açıklama                                   |
|----------------|--------------------------------------------|
| IVaccineRepo | Aşılarla ilgili veritabanı işlemleri için arayüz |
| IDoctorRepo | Veteriner hekimleriyle ilgili veritabanı işlemleri için arayüz |
| ICustomerRepo | Hayvan sahipleriyle ilgili veritabanı işlemleri için arayüz |
| IAvailableDateRepo | Müsait günlerle ilgili veritabanı işlemleri için arayüz |
| IAppointmentRepo | Randevularla ilgili veritabanı işlemleri için arayüz |
| IAnimalRepo | Randevularla ilgili veritabanı işlemleri için arayüz |


### MANAGER Tablosu:

| Manager Adı | Açıklama                                  |
|-------------|-------------------------------------------|
| VaccineManager | Aşılarla ilgili iş katmanı işlemleri için kullanılır |
| DoctorManager  | Veteriner hekimleriyle ilgili iş katmanı işlemleri için kullanılır |
| CustomerManager | Hayvan sahipleriyle ilgili iş katmanı işlemleri için kullanılır |
| AvailableDateManager | Müsait günlerle ilgili iş katmanı işlemleri için kullanılır |
| AppointmentManager | Randevularla ilgili iş katmanı işlemleri için kullanılır |
| AnimalManager | Hayvanlarla ilgili iş katmanı işlemleri için kullanılır |

### CONTROLLER Tablosu:

| Controller Adı | Açıklama                                      |
|----------------|-----------------------------------------------|
| VaccineController | Aşılarla ilgili API endpoint'lerini temsil eder |
| DoctorController  | Veteriner hekimleriyle ilgili API endpoint'lerini temsil eder |
| CustomerController | Hayvan sahipleriyle ilgili API endpoint'lerini temsil eder |
| AvailableDateController | Müsait günlerle ilgili API endpoint'lerini temsil eder |
| AppointmentController | Randevularla ilgili API endpoint'lerini temsil eder |
| AnimalController | Hayvanlarla ilgili API endpoint'lerini temsil eder |

## Uygulamayı Başlatma

Proje Spring Boot ile geliştirildiği için, uygulamayı ayağa kaldırmak için aşağıdaki adımları izleyebilirsiniz:

1. Proje kaynak kodlarını bilgisayarınıza indirin.
2. PostgreSQL veritabanınızı oluşturun ve bağlantı bilgilerini `application.properties` dosyasında güncelleyin.
3. Projeyi bir IDE'de (IntelliJ IDEA, Eclipse vb.) açın.
4. `VeterinaryAppApplication` sınıfını bulun ve çalıştırın.
5. Uygulama başlatıldığında, [http://localhost:8080](http://localhost:8080/swagger-ui/index.html#/) adresinden API'ye erişebilirsiniz.

## API Endpoints

Aşağıda, API'nin sunduğu temel endpoint'lerin bir listesi bulunmaktadır:

| Endpoint                                | HTTP Metodu | Açıklama                                    |
|-----------------------------------------|-------------|---------------------------------------------|
| /v1/vaccines/{id}                       | GET         | Belirtilen ID'ye sahip aşı hangi hayvanlara uygulandığını gösterir       |
| /v1/vaccines/                           | PUT         | Belirtilen ID'ye sahip aşıyı güncelle     |
| /v1/vaccines/{id}                       | DELETE      | Belirtilen ID'ye sahip aşıyı sil          |
| /v1/vaccines                            | GET         | Tüm aşıları getir                          |
| /v1/vaccines                            | POST        | Aşı ekle                                    |
| /v1/vaccines/findByDate                 | GET         | Belirli tarihe göre süresi yaklaşan aşıları getirir.  |

| Endpoint                                | HTTP Metodu | Açıklama                                    |
|-----------------------------------------|-------------|---------------------------------------------|
| /v1/doctors/{id}                        | GET         | Belirtilen ID'ye sahip veteriner hekimini getir |
| /v1/doctors/{                        | PUT         | Belirtilen ID'ye sahip veteriner hekimini güncelle |
| /v1/doctors/{id}                        | DELETE      | Belirtilen ID'ye sahip veteriner hekimini sil |
| /v1/doctors                             | GET         | Tüm veteriner hekimlerini getir            |
| /v1/doctors                             | POST        | Veteriner hekimi ekle                      |

| Endpoint                                | HTTP Metodu | Açıklama                                    |
|-----------------------------------------|-------------|---------------------------------------------|
| /v1/customers/{id}                      | GET         | Belirtilen ID'ye sahip hayvan sahibini getir |
| /v1/customers/                          | PUT         | Belirtilen ID'ye sahip hayvan sahibini güncelle |
| /v1/customers/{id}                      | DELETE      | Belirtilen ID'ye sahip hayvan sahibini sil |
| /v1/customers                           | GET         | Tüm hayvan sahiplerini getir               |
| /v1/customers                           | POST        | Hayvan sahibi ekle                         |
| /v1/customers/getByName/{name}          | GET         | İsme göre hayvan sahipleri                 |
| /v1/customers/{id}/animals              | GET         | ID'ye kayıtlı hayvanları getirir.                 |

| Endpoint                             | HTTP Metodu | Açıklama                                    |
|--------------------------------------|-------------|---------------------------------------------|
| /v1/animals                          | GET         | Tüm hayvanları getir                       |
| /v1/animals                          | PUT         | Hayvanı güncelle                           |
| /v1/animals                          | POST        | Yeni bir hayvan ekle                       |
| /v1/animals/{id}                     | GET         | Belirtilen ID'ye sahip hayvanı getir       |
| /v1/animals/{id}                     | DELETE      | Belirtilen ID'ye sahip hayvanı sil         |
| /v1/animals/{id}/vaccines            | GET         | Belirtilen hayvanın tüm aşılarını getir    |
| /v1/animals/getByName/{name}         | GET         | İsim ile hayvanları getir                  |

| Endpoint                                | HTTP Metodu | Açıklama                                    |
|-----------------------------------------|-------------|---------------------------------------------|
| /v1/available_dates/{id}                | GET         | Belirtilen ID'ye sahip müsait günü getir   |
| /v1/available_dates/                  | PUT         | Belirtilen ID'ye sahip müsait günü güncelle |
| /v1/available_dates/{id}                | DELETE      | Belirtilen ID'ye sahip müsait günü sil     |
| /v1/available_dates                     | GET         | Tüm müsait günleri getir                   |
| /v1/available_dates                     | POST        | Müsait gün ekle                             |

| Endpoint                                   | HTTP Metodu | Açıklama                                    |
|--------------------------------------------|-------------|---------------------------------------------|
| /v1/appointments                           | GET         | Tüm randevuları getir                      |
| /v1/appointments                           | PUT         | Randevuyu güncelle                         |
| /v1/appointments                           | POST        | Yeni bir randevu oluştur                   |
| /v1/appointments/{id}                      | GET         | Belirtilen ID'ye sahip randevuyu getir     |
| /v1/appointments/{id}                      | DELETE      | Belirtilen ID'ye sahip randevuyu sil       |
| /v1/appointments/{id}/findByDateAndDoctor  | GET         | Belirtilen tarih ve veteriner hekime göre randevuları getir |
| /v1/appointments/{id}/findByDateAndAnimal  | GET         | Belirtilen tarih ve hayvana göre randevuları getir |

## Görselleri incelemek için [tıklayınız](https://github.com/ismetonursahin/Week-12/tree/main/veterinary_app/img)
