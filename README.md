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
|/parkour                       | コマンドのリストを表示します。| None|
|/parkour list                  | 現在登録されているParkourの一覧を取得します。| None |
|/parkour info \<id>            | 指定したParkourの情報を表示します。| None |
|/parkour add \<id>             | IDが存在しない場合にそのIDのParkourを追加します。| None |
|/parkour setstart \<id>        | 指定したParkourのスタート地点を設定します。| None |
|/parkour setend \<id>          | 指定したParkourのゴール地点を設定します。| None |
|/parkour setpre \<id>          | 指定したParkourのプリセット地点を設定します。| None |
|/parkour setname \<id> \<name> | 指定したParkourの表示名を設定します。| None |
|/parkour active \<id>          | 指定したParkourをプレイ可能の状態に変更します。| None |
|/parkour remove \<id>          | 指定したParkourの登録を解除（削除）します。| None |
|/parkour teleport \<id>        | 指定したParkourのプリセット地点へテレポートします。| None |
|/parkour reload                | parkours.jsonの再読み込み、プレイヤーの計測のリセット| None |

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
