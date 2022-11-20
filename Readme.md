Наименование пользователя: BlackHysteria
Название проекта: trunk-based-development
Токен для гитхаба действует до 24 ноября: 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

Работу проекта можно проверить при вызове команды git push или при вызове get-запроса.
В файле api.http есть пример запроса для принудительного запуска процеccа и ещё запрос для получения всех коммитов проекта от github (участвует в работе).

* Как тестировать:
1. Скопировать проект из репозитория: https://github.com/BlackHysteria/trunk-based-development.git
1. Запустить приложение в IDE (класс TrunkBasedDevelopmentApplication).
2. Убедиться после копирования из репозитория тесты проходят успешно (класс GitHubServiceTest).

#### В рамках тестирования можно проверить три последовательности действий.

* Push без изменений.
1. Никаких новых коммитов нет, локальная версия равна в удалённом репозитории.
2. Сделать push.
3. Ожидать результат в консоли: screens/nothing.png


* Push с изменениями и положительном выполнении тестов.
1. Сделать любое изменение в проекте.
   Проверить что сборка Maven собирается (mvn clean test -> BUILD_SUCCESS).
2. Сделать локальный коммит (git add . + git commit) или через интерфейс.
3. Сделать push.
4. Ожидать результат в консоли: screens/success.png


* Push с изменениями и негативное выполнении тестов.
1. Сломать любой из двух тестов в классе GitHubServiceTest (например, поменять первый аргумент в assertEquals).
   Проверить что сборка Maven не собирается (mvn clean test -> BUILD FAILURE).
2. Сделать локальный коммит (git add . + git commit) или через интерфейс.
3. Сделать push.
4. Ожидать результат в консоли screens/failed.png