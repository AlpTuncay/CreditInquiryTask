# Kredi Sorgulama Sistemi

Kullanıcıların kredi başvurusu yapabilmeleri için gerekli olan servisleri içerir.

## Servisler

### Credit Inquiry Frontend
Static içeriğin kullanıcılara servis edildiği frontend servisi. Kullanıcıdan gerekli bilgileri alabilmek için bir form içerir ve bu form ile backend servisine sorgu gönderilir.

### Credit Inquiry
Java Spring Framework ile yazılmış backend servisi. Frontendden gelen bilgiler ve önceden tanımlanan kurallara göre kredi başvurusunu sonuçlandırır.

### Kurallar
  * Kredi skoru 500’ün altında ise kullanıcı reddedilir.
  * Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000 TL’nin altında
ise kullanıcının kredi başvurusu reddedilir ve eğer 5000 TL'nin üzerindeyse başvuru onaylanır ve 10000 TL limit atanır.
  * Kred skoru 1000 puana eş t veya üzer nde se kullanıcıya AYLIK GELİR BİLGİSİ \*
KREDİ LİMİT ÇARPANI kadar limit atanır.

## Nasıl Çalışır?
Her iki servis de Docker ile containerlara dönüştürüldü. Bu containerlar Docker Compose yardımı ile çalıştırlıyor.

> docker-compose up --build

Komutu ile servisler çalıştırılır.

Uygulamaya erişmek için browserda

> localhost:3000

adresine gidilir.
