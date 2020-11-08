I profili si possono usare in DUE modi.

1) in 'application.properties' si definisce

        spring.profiles.active=common,corrado

   (1 o piu' altri profili).
   In questo viene caricato 'application.properties' e automaticamente
   vengono caicati i file 'application-<profile>.properties'.

2) si passa da LINEA DI COMANDO la property

    -Dspring.profiles.active=common,corrado

   In questo modo vengono caricati i vari 'application-<profile>.properties'.
   Dentro ogni file si puo' inserire

        spring.profiles.include=default

   In questo modo, il contenuto di altri 'application-<profile>.properties'
   (per 'default' dovrebbe essere direttamente 'application.properties')
   vengono inclusi

3) la property 'spring.profiles' sembra sia usata in YAML per FILTRARE quali
   profili sono attivati. Se viene attivato un profilo della lista, ok, come 2)
   altrimenti il profiloviene ignorato