# Parkour

## Summary
Parkourのタイム計測をサポートするBukkitプラグイン。未だ開発途中の為、コードに煩雑な部分があります。

## Features
- JDK8のもと開発しています。  
- Bukkitに内包されているGsonライブラリを使用している為、bukkit:reloadでエラーを引き起こす可能性があります。
- ファイルの再読込には/parkour reloadコマンドを使用して下さい。

##配布
http://www.mediafire.com/file/gmys3lbbpzf2hmc/Parkour-2.1.0-SNAPSHOT.jar

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
|/parkour active \<id>          |指定したParkourをプレイ可能にします。|parkour.command.active|
|/parkour lock \<id>          |指定したParkourをプレイ不可能にします。|parkour.command.lock|
|/parkour remove \<id>          |指定したParkourの登録を解除（削除）します。|parkour.command.remove|
|/parkour teleport \<id>        |指定したParkourのプリセット地点へテレポートします。|parkour.command.teleport|
|/parkour reload                |parkours.jsonの再読み込み、プレイヤーの計測のリセット|parkour.command.reload|
|/parkour save \<id>            |指定したParkourのセーブ可能/不可能を切り替えます。|parkour.command.save|

### Permissions
|Permission|Description|
|:---------|:----------|
|parkour.use      | Parkourのプレイを許可します。|
|parkour.command.*| プラグインの全てコマンドの使用を許可します。|

### Plans
- config.ymlの設定用コマンド
- コマンドでのタイムレコード表示
- タイムレコードのホログラム表示
-　スタート及びゴールのホログラム表示
- ゴール直後のテレポートのサポート

### Events
- PlayerParkourStartEvent  
- PlayerParkourSucceedEvent  
- PlayerParkourFailEvent  

## License
このBukkitプラグインはMITライセンスをもとに公開されています。ライセンス本文は[こちら][license]をご覧下さい。

***
## Contact
バグ、質問等ございましたら下記Twitterまで。  
Twitter : [@fon_sky_lock][twitter]

[twitter]:https://twitter.com/fon_sky_lock
[license]:https://opensource.org/licenses/mit-license.php