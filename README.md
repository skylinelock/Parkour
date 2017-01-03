# Parkour

## Summary
Parkourのタイム計測をサポートするBukkitプラグイン。未だ開発途中です。

## Features
- Java8の機能を利用しています。  
- Bukkitに内包されているGsonライブラリを使用している為、bukkit:reloadでエラーを引き起こす可能性があります。

## Description
### Commands
|Usage|Description|Permission|
|-----|:-----------|:-------|
|/parkour                       |コマンドのリストを表示します。|parkour.command.usage|
|/parkour list                  |現在登録されているParkourの一覧を取得します。|parkour.command.list|
|/parkour info \<id>            |指定したParkourの情報を表示します。|parkour.command.info|
|/parkour add \<id>             |IDが存在しない場合にそのIDのParkourを追加します。|parkour.command.add|
|/parkour setstart \<id>        |指定したParkourのスタート地点を設定します。|parkour.command.setstart|
|/parkour setend \<id>          |指定したParkourのゴール地点を設定します。|parkour.command.setend|
|/parkour setpre \<id>          |指定したParkourのプリセット地点を設定します。|parkour.command.setpre|
|/parkour setname \<id> \<name> |指定したParkourの表示名を設定します。|parkour.command.setname|
|/parkour active \<id>          |指定したParkourをプレイ可能の状態に変更します。|parkour.command.active|
|/parkour remove \<id>          |指定したParkourの登録を解除（削除）します。|parkour.command.remove|
|/parkour teleport \<id>        |指定したParkourのプリセット地点へテレポートします。|parkour.command.teleport|
|/parkour reload                |parkours.jsonの再読み込み、プレイヤーの計測のリセット|parkour.command.reload|

###Permissions
|Permission|Description|
|----------|-----------|
|parkour.use      | Parkourの使用を許可します。|
|parkour.command.*| プラグインの全てコマンドの仕様を許可します。|

### Plans
- Permissionの実装
- config.ymlの設定用コマンド
- MongoDBを用いたタイムレコードの保持
- リアルタイムでタイムレコードを表示
- ゴール直後のテレポートのサポート
- ライセンスの追加

### Events
- PlayerParkourStartEvent  
- PlayerParkourSucceedEvent  
- PlayerParkourFailEvent  

***
## Contact
バグ、質問等ございましたら下記Twitterまで。  
Twitter : [@fon_sky_lock][twitter]

[twitter]:https://twitter.com/fon_sky_lock
