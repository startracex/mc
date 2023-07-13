---
layout: '~/layouts/Docs.astro'
index: 3
time: 2023-07-13
---

# 资源和数据

完善之前创建的 `obsidian_pickaxe` 和 `obsidian_brick`

这些资源位于根目录的 `src/main/resources` 下

## 翻译

翻译文件位于 [src/main/resources/assets/MOD_ID/lang/*.json](/mc/resources/assets/startracex-mod/lang/zh_cn.json)

```json
{
   "itemgroup.MOD_ID.obsidian_group": "Obsidian Group",
  "item.MOD_ID.obsidian_pickaxe": "Obsidian Pickaxe",
  "item.MOD_ID.obsidian_brick": "Obsidian Brick",
}
```

## 材质和模型

item的模型文件位于 [src/main/resources/assets/MOD_ID/models/item/*.json](/mc/resources/assets/startracex-mod/models/item/obsidian_pickaxe.json)

```json
{
  "parent": "item/handheld",
  "textures": {
    "layer0": "MOD_ID:item/obsidian_pickaxe"
  }
}
```

item的材质文件位于 [src/main/resources/assets/MOD_ID/textures/item/*.png](/mc/resources/assets/startracex-mod/textures/item/obsidian_pickaxe.png)

上面的json将会使用这个png作为材质

Block的模型文件位于 [src/main/resources/assets/MOD_ID/models/block/*.json](/mc/resources/assets/startracex-mod/models/block/obsidian_brick.json)

```json
{
  "parent": "block/cube_all",
  "textures": {
    "all": "MOD_ID:block/obsidian_brick"
  }
}
```

Block的材质文件位于 [src/main/resources/assets/MOD_ID/textures/block/*.png](/mc/resources/assets/startracex-mod/textures/block/obsidian_brick.png)

上面的json将会使用这个png作为六个面的材质

## 配方

配方文件位于 [src/main/resources/data/MOD_ID/recipes/*.json](/mc/resources/data/startracex-mod/recipes/obsidian_brick.json)

下面的配方将会合成 `obsidian_brick`

原材料为四个黑曜石和一个铁锭, 合成四个 `obsidian_brick`

```json
{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "O O",
    " I ",
    "O O"
  ],
  "key": {
    "O": {
      "item": "minecraft:obsidian"
    },
    "I": {
      "item": "minecraft:iron_ingot"
    }
  },
  "result": {
    "item": "startracex-mod:obsidian_brick",
    "count": 4
  }
}
```
`obsidian_pickaxe` 的配方同理

## 标签

现在来设定挖掘等级

控制稿子的可挖掘物品位于 [src/main/resources/data/minecraft/tags/mineable/pickaxe.json](/mc/resources/data/minecraft/tags/blocks/mineable/pickaxe.json)

```json
{
  "replace": false,
  "values": [
    "MOD_ID:obsidian_brick"
  ]
}
```

上面的配置将会让稿子可以挖掘 `obsidian_brick`, 并且不将原本可以挖掘的方块移除

设置需要稿子的等级, 将 `"MOD_ID:obsidian_brick"` 放入相应等级的json文件中

它们是

* 石制工具 src/main/resources/data/minecraft/tags/blocks/mineable/needs_stone_tool.json
* 铁质工具 src/main/resources/data/minecraft/tags/blocks/mineable/needs_iron_tool.json
* 钻石工具 src/main/resources/data/minecraft/tags/blocks/mineable/needs_diamond_tool.json
* 下届合金工具 src/main/resources/data/farbic/tags/blocks/needs_tool_level_4.json
