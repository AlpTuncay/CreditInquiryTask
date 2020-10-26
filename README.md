# Kredi Sorgulama Sistemi

Kullanıcıların kredi başvurusu yapabilmeleri için gerekli olan servisleri içerir.

## Servisler

### Credit Inquiry Frontend
Static içeriğin kullanıcılara servis edildiği frontend servisi. Kullanıcıdan gerekli bilgileri alabilmek için bir form içerir ve bu form ile backend servisine sorgu gönderilir.

### Credit Inquiry
Java Spring Framework ile yazılmış backend servisi. Frontendden gelen bilgilere göre kredi başvurusunu sonuçlandırır.

## Nasıl Çalışır?
Her iki servis de Docker ile containerlara dönüştürüldü. Bu containerlar Docker Compose yardımı ile çalıştırlıyor.

> docker-compose up --build

Komutu ile servisler çalıştırılır.

Uygulamaya erişmek için browserda

> localhost:3000

adresine gidilir.
