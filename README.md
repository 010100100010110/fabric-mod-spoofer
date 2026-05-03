# Fabric Mod Spoofer

Фабрик мод для перехвата и подмены пакетов, отправляемых серверу при проверке установленных модов.

## Функциональность

✅ **Перехват всех пакетов** - Мод отслеживает все пакеты, отправляемые на сервер

✅ **Подмена списка модов** - Автоматически заменяет список установленных модов на разрешенный

✅ **Скрытый список** - Список разрешенных модов закодирован в Base64 (не виден в исходном коде)

✅ **Логирование** - Все попытки проверки модов логируются в `mods/.modspoofer/`

✅ **Оповещение** - Игрок видит уведомления о попытках проверки модов

## Включенные моды

- cicada-lib (0.14.3+1.21.9-1.21.10)
- cloth-config (21.11.153-fabric)
- dynamiccrosshair (9.11+1.21.11-fabric)
- fabric-api (0.141.3+1.21.11)
- fabric-language-kotlin (1.13.11+kotlin.2.3.21)
- iris-fabric (1.10.7+mc1.21.11)
- MouseTweaks-fabric (mc1.21.11-2.30)
- owo-lib (0.13.0+1.21.11)
- reeses-sodium-options (fabric-2.0.3+mc1.21.11)
- Searchables (fabric-1.21.11-1.0.4)
- show-me-your-skin (2.0.2+1.21.11)
- shulkerboxtooltip (fabric-5.2.16+1.21.11)
- skinlayers3d (fabric-1.11.1-mc1.21.11)
- skinshuffle (2.10.2+1.21.11-fabric)
- sodium (fabric-0.8.7+mc1.21.11)
- voicechat (fabric-1.21.11-2.6.16)
- worldedit-mod (7.4.2-1.21.11)

## Установка

1. Скачайте последний JAR из [Releases](https://github.com/010100100010110/fabric-mod-spoofer/releases)
2. Поместите его в папку `mods/`
3. Запустите Minecraft с Fabric Loader

## Компиляция

```bash
git clone https://github.com/010100100010110/fabric-mod-spoofer.git
cd fabric-mod-spoofer
./gradlew build
```

Дело будет в `build/libs/mod-spoofer-1.0.0.jar`

## Логи

Мод создает два файла логирования в `mods/.modspoofer/`:

- `mod_check_attempts.log` - Попытки проверки модов сервером
- `activity.log` - Общая активность мода

## Структура проекта

```
src/main/java/com/modspoofer/
├── ModSpoofer.java           # Главный класс мода
├── mixin/
│   └── ClientConnectionMixin.java   # Перехват пакетов
└── util/
    ├── PacketInterceptor.java       # Обработка пакетов
    ├── AllowedModsList.java         # Список разрешенных модов
    └── ModLogger.java               # Логирование
```

## Как это работает

1. Мод перехватывает все пакеты через Mixin на `ClientConnection`
2. Проверяет, является ли пакет проверкой модов (CustomPayloadC2SPacket)
3. Если обнаружена проверка модов, подменяет список на разрешенный
4. Логирует все попытки в файлы логирования
5. Выводит уведомления в chat игроку

## Поддерживаемые версии

- Minecraft: 1.21.1
- Fabric Loader: 0.16.9+
- Java: 21+

## Лицензия

MIT

## Автор

Copilot & 010100100010110
