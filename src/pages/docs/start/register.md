---
layout: '~/layouts/Docs.astro'
index: 2
time: 2023-07-11
---

# 注册

此文档中的路径相对于 Java 包名 (也就是`onInitialize` 所在类所在的目录)

例如 [此项目](https://github.com/startracex/mc) 中的 `registry/modItem` 类位于 `src/main/java/games/startrace/registry/modItem`

## 注册物品

为方便起, 将命名空间设置为一个字符常量 `MOD_ID`

```java
public class MainMod implements ModInitializer {
    public static final String MOD_ID = "startracex-mod";
}
```

`onInitialize` 被调用时 为新增物品初始化, 我将一些物品其放在了 `registry/modItem` 类中

```java
public class modItem {
   public static Item CUSTOM_ITEM = Registry.register(Registries.ITEM, new Identifier(MOD_ID, NAME), item);
   public static void init() {}
}
```
并将`init`添加到 `onInitialize`
   
```java
public class MainMod implements ModInitializer {
    public static final String MOD_ID = "startracex-mod";
    @Override
    public void onInitialize() {
        modItem.init();
    }
}
```
当`init`被调用时, 静态字段将被注册


## 注册物品组

若要注册物品组,则要声明一个物品组的静态字段,它的类型为`RegistryKey<ItemGroup>` ()在旧版本中为`ItemGroup`)

我将这些组放在了 `registry/modItemGroup` 类中, 并在 `init` 中注册

```java
public class modItemGroup {
    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MainMod.MOD_ID, extraName));
    public static void init() {
        Registry.register(Registries.ITEM_GROUP, CUSTOM_GROUP, FabricItemGroup.builder()
                .icon(() -> new ItemStack(ICON_ITEM))
                .displayName(Text.translatable("itemgroup." + MOD_ID + ".NAME"))
                .build());
    }
}
```
必须调用`displayName(Text.translatable(String))`以设置物品组的名称

为了简写上面的代码, 可以新增一个方法用于注册新的物品组

`ICON_ITEM` 物品组图标

`Name` 物品组名称

```java
public class modItemGroup {
    public static RegistryKey<ItemGroup> createItemGroup(Item ICON_ITEM, String Name) {
        var CUSTOM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MainMod.MOD_ID, Name));
        Registry.register(Registries.ITEM_GROUP, CUSTOM_GROUP, FabricItemGroup.builder()
                .icon(() -> new ItemStack(ICON_ITEM))
                .displayName(Text.translatable("itemgroup." + MainMod.MOD_ID + "." + Name))
                .build());
        return CUSTOM_GROUP;
    }
}
```

之后就可以这样使用
   
```java
public class modItemGroup {
   public static RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP = createItemGroup(ITEM, NAME);
   public static RegistryKey<ItemGroup> createItemGroup(...){...}
   public static void init(){}
}
```

现在可以新增一个方法用于注册新的物品, 并选择将其添加到物品组中

`name` 物品名称

`item` 物品示例

`groups` 所属组

```java
public class modItem {
    public static Item createItem(String name, Item item, RegistryKey<ItemGroup>... groups) {
        var CUSTOM_ITEM = Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
        for (RegistryKey<ItemGroup> group : groups) {
            ItemGroupEvents.modifyEntriesEvent(group).register(e -> e.add(item));
        }
        return CUSTOM_ITEM;
    }
}
```

之后就可以这样使用

```java
public class modItem {
    public static Item CUSTOM_ITEM = createItem(NAME, ITEM, GROUP...);
    public static Item createItem(...){...}
    public static void init(){}
}
```

## 注册方块

若要注册方块,则要声明一个方块的静态字段,它的类型为`Block`

```java
public class modBlock {
    public static Block CUSTOM_BLOCK = new Block(FabricBlockSettings.create()...),
    public static void init() {}
}
```

我将这些组放在了 `registry/modBlock` 类中, 并在 `init` 中注册

可以将简化一下

```java
public class modBlock {
    public static Block createBlock(String name, Block block, RegistryKey<ItemGroup>... groups) {
        modItem.createItem(name, new BlockItem(block, new FabricItemSettings()), groups);
        Registry.register(Registries.BLOCK, new Identifier(MainMod.MOD_ID, name), block);
        return block;
    }
}
```
之后就可以这样使用

```java
public class modBlock {
    public static Block CUSTOM_BLOCK = createBlock(NAME, BLOCK, GROUP...);
    public static Block createBlock(...){...}
    public static void init(){}
}
```

## 示例: 添加一个obsidian_pickaxe

创建一个物品类, 可在注释部分自定义稿子的属性
    
```java
public class Pickaxe implements ToolMaterial {
    public static final Pickaxe ObsidianPickaxeINSTANCE = new Pickaxe();
    /* 
    ...
     */
}
```

由于pickaxe是私有的,所以需要创建一个新的类,将其公开

```java
public class _Pickaxe extends PickaxeItem {
    public _Pickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
```

将 `ObsidianPickaxeINSTANCE` 注册到物品,并添加物品组

```java
public class modItem {
    public static final Item obsidian_pickaxe = createItem("obsidian_pickaxe",
            new _Pickaxe(
                    Pickaxe.ObsidianPickaxeINSTANCE, 79, 0, new Item.Settings())
            , modItemGroup.OBSIDIAN_GROUP);
    // ...
}

```

## 示例: 添加一个obsidian_brick

创建obsidian_brick, 设置它的名称和硬度, 声明需要工具加快采集, 注册到物品, 并将其添加到一个物品组

```java
public static Block ObsidianBrickINSTANCE = modBlock.createBlock("obsidian_brick",
        new Block(FabricBlockSettings.create().hardness(200f).requiresTool()),
        modItemGroup.OBSIDIAN_GROUP
);
```