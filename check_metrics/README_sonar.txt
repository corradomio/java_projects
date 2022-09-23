SonarQube Web API

    ajs_anonymous_id=0c2f69c8-98de-45fe-a530-d7f880bb6362; Webstorm-675c8b73=0b32a744-2a19-4961-ace4-a2a3cdaced8d; XSRF-TOKEN=g9ugvaejtu7qq2lrm434kmbftk; JWT-SESSION=eyJhbGciOiJIUzI1NiJ9.eyJsYXN0UmVmcmVzaFRpbWUiOjE2NjM4MjE0OTk4ODQsInhzcmZUb2tlbiI6Imc5dWd2YWVqdHU3cXEybHJtNDM0a21iZnRrIiwianRpIjoiQVlNN3Frdl94RnVsU1dGUXVzVGEiLCJzdWIiOiJBWU0yUnRNNzR3YTl6VFg1c1RCdCIsImlhdCI6MTY2MzE1MzM1OSwiZXhwIjoxNjY0MDgwNjk5fQ.-gEfHcF9dQ0e6dybgHi6M14EcIPvhyWRIEmIzPYO_Vs

    current user:


    using the browser

        http://localhost:9000/api -> http://localhost:9000/api/webservices/list

    using curl

        curl -u admin:password http://localhost:9000/api/webservices/list

    using wget

        wget --user=admin --password=password http://localhost:9000/api/webservices/list
        DOESN'T work!!!
        WHY???
