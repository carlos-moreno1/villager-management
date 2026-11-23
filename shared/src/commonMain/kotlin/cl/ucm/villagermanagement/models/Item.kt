package cl.ucm.villagermanagement.models

enum class ItemCategory {
    FARMING_AND_FOOD,       // Farmer, Butcher
    MINERALS_AND_CURRENCY,  // Armorer, Toolsmith, Weaponsmith, Cleric, etc.
    LUMBER_AND_WOOD,        // Fletcher
    BOOKS_AND_PAPER,        // Librarian, Cartographer
    WEAPONS_AND_TOOLS,      // Weaponsmith, Toolsmith, Fletcher
    ARMOR_AND_APPAREL,      // Armorer, Leatherworker
    BLOCKS_AND_DECORATION,   // Mason, Shepherd
    FISH_AND_OCEAN,         // Fisherman
    MISCELLANEOUS_TRADES    // Cleric, Shepherd, etc.
}

enum class Item(
    val minecraftId: String,
    val displayName: String,
    val category: ItemCategory
) {
    EMERALD("minecraft:emerald", "Emerald", ItemCategory.MINERALS_AND_CURRENCY),

    // --- AGRICULTURA Y ALIMENTOS (Farmer / Butcher) ---
    WHEAT("minecraft:wheat", "Wheat", ItemCategory.FARMING_AND_FOOD),
    CARROT("minecraft:carrot", "Carrot", ItemCategory.FARMING_AND_FOOD),
    POTATO("minecraft:potato", "Potato", ItemCategory.FARMING_AND_FOOD),
    BEETROOT("minecraft:beetroot", "Beetroot", ItemCategory.FARMING_AND_FOOD),
    PUMPKIN("minecraft:pumpkin", "Pumpkin", ItemCategory.FARMING_AND_FOOD),
    MELON("minecraft:melon", "Melon", ItemCategory.FARMING_AND_FOOD),
    BREAD("minecraft:bread", "Bread", ItemCategory.FARMING_AND_FOOD),
    APPLE("minecraft:apple", "Apple", ItemCategory.FARMING_AND_FOOD),
    COOKIE("minecraft:cookie", "Cookie", ItemCategory.FARMING_AND_FOOD),
    CAKE("minecraft:cake", "Cake", ItemCategory.FARMING_AND_FOOD),
    GOLDEN_CARROT("minecraft:golden_carrot", "Golden Carrot", ItemCategory.FARMING_AND_FOOD),
    GLISTERING_MELON_SLICE("minecraft:glistering_melon_slice", "Glistering Melon Slice", ItemCategory.FARMING_AND_FOOD),
    SUSPICIOUS_STEW("minecraft:suspicious_stew", "Suspicious Stew", ItemCategory.FARMING_AND_FOOD),
    RAW_CHICKEN("minecraft:chicken", "Raw Chicken", ItemCategory.FARMING_AND_FOOD),
    COOKED_CHICKEN("minecraft:cooked_chicken", "Cooked Chicken", ItemCategory.FARMING_AND_FOOD),
    RAW_PORKCHOP("minecraft:porkchop", "Raw Porkchop", ItemCategory.FARMING_AND_FOOD),
    COOKED_PORKCHOP("minecraft:cooked_porkchop", "Cooked Porkchop", ItemCategory.FARMING_AND_FOOD),
    RAW_BEEF("minecraft:beef", "Raw Beef", ItemCategory.FARMING_AND_FOOD),
    COOKED_BEEF("minecraft:cooked_beef", "Steak", ItemCategory.FARMING_AND_FOOD),
    RAW_MUTTON("minecraft:mutton", "Raw Mutton", ItemCategory.FARMING_AND_FOOD),
    COOKED_MUTTON("minecraft:cooked_mutton", "Cooked Mutton", ItemCategory.FARMING_AND_FOOD),
    RAW_RABBIT("minecraft:rabbit", "Raw Rabbit", ItemCategory.FARMING_AND_FOOD),
    RABBIT_STEW("minecraft:rabbit_stew", "Rabbit Stew", ItemCategory.FARMING_AND_FOOD),
    RABBIT_HIDE("minecraft:rabbit_hide", "Rabbit Hide", ItemCategory.FARMING_AND_FOOD),
    SWEET_BERRIES("minecraft:sweet_berries", "Sweet Berries", ItemCategory.FARMING_AND_FOOD),

    // --- MATERIALES, MINERALES Y ALQUIMIA (Cleric, Armorer, Toolsmith, Mason, etc.) ---
    COAL("minecraft:coal", "Coal", ItemCategory.MINERALS_AND_CURRENCY),
    IRON_INGOT("minecraft:iron_ingot", "Iron Ingot", ItemCategory.MINERALS_AND_CURRENCY),
    GOLD_INGOT("minecraft:gold_ingot", "Gold Ingot", ItemCategory.MINERALS_AND_CURRENCY),
    DIAMOND("minecraft:diamond", "Diamond", ItemCategory.MINERALS_AND_CURRENCY),
    LAPIS_LAZULI("minecraft:lapis_lazuli", "Lapis Lazuli", ItemCategory.MINERALS_AND_CURRENCY),
    REDSTONE("minecraft:redstone", "Redstone Dust", ItemCategory.MINERALS_AND_CURRENCY),
    NETHER_QUARTZ("minecraft:quartz", "Nether Quartz", ItemCategory.MINERALS_AND_CURRENCY),
    ROTTEN_FLESH("minecraft:rotten_flesh", "Rotten Flesh", ItemCategory.MISCELLANEOUS_TRADES),
    ENDER_PEARL("minecraft:ender_pearl", "Ender Pearl", ItemCategory.MISCELLANEOUS_TRADES),
    BLAZE_POWDER("minecraft:blaze_powder", "Blaze Powder", ItemCategory.MISCELLANEOUS_TRADES),
    GLASS_BOTTLE("minecraft:glass_bottle", "Glass Bottle", ItemCategory.MISCELLANEOUS_TRADES),
    EXPERIENCE_BOTTLE("minecraft:experience_bottle", "Bottle o' Enchanting", ItemCategory.MISCELLANEOUS_TRADES),

    // --- MADERA Y FLECHERÍA (Fletcher) ---
    STICK("minecraft:stick", "Stick", ItemCategory.LUMBER_AND_WOOD),
    FLINT("minecraft:flint", "Flint", ItemCategory.LUMBER_AND_WOOD),
    STRING("minecraft:string", "String", ItemCategory.LUMBER_AND_WOOD),
    FEATHER("minecraft:feather", "Feather", ItemCategory.LUMBER_AND_WOOD),
    GRAVEL("minecraft:gravel", "Gravel", ItemCategory.LUMBER_AND_WOOD),
    ARROW("minecraft:arrow", "Arrow", ItemCategory.WEAPONS_AND_TOOLS),
    TIPPED_ARROW("minecraft:tipped_arrow", "Tipped Arrow", ItemCategory.WEAPONS_AND_TOOLS),
    BOW("minecraft:bow", "Bow", ItemCategory.WEAPONS_AND_TOOLS),
    CROSSBOW("minecraft:crossbow", "Crossbow", ItemCategory.WEAPONS_AND_TOOLS),

    // --- LIBROS, PAPEL Y MAPAS (Librarian / Cartographer) ---
    PAPER("minecraft:paper", "Paper", ItemCategory.BOOKS_AND_PAPER),
    BOOK("minecraft:book", "Book", ItemCategory.BOOKS_AND_PAPER),
    WRITTEN_BOOK("minecraft:written_book", "Written Book", ItemCategory.BOOKS_AND_PAPER),
    ENCHANTED_BOOK("minecraft:enchanted_book", "Enchanted Book", ItemCategory.BOOKS_AND_PAPER),
    BOOKSHELF("minecraft:bookshelf", "Bookshelf", ItemCategory.BOOKS_AND_PAPER),
    LANTERN("minecraft:lantern", "Lantern", ItemCategory.BOOKS_AND_PAPER),
    MAP("minecraft:map", "Empty Map", ItemCategory.BOOKS_AND_PAPER),
    FILLED_MAP("minecraft:filled_map", "Explorer Map", ItemCategory.BOOKS_AND_PAPER),
    COMPASS("minecraft:compass", "Compass", ItemCategory.BOOKS_AND_PAPER),
    GLASS_PANE("minecraft:glass_pane", "Glass Pane", ItemCategory.BOOKS_AND_PAPER),

    // --- HERRAMIENTAS Y ARMAS (Toolsmith / Weaponsmith) ---
    IRON_AXE("minecraft:iron_axe", "Iron Axe", ItemCategory.WEAPONS_AND_TOOLS),
    DIAMOND_AXE("minecraft:diamond_axe", "Diamond Axe", ItemCategory.WEAPONS_AND_TOOLS),
    IRON_PICKAXE("minecraft:iron_pickaxe", "Iron Pickaxe", ItemCategory.WEAPONS_AND_TOOLS),
    DIAMOND_PICKAXE("minecraft:diamond_pickaxe", "Diamond Pickaxe", ItemCategory.WEAPONS_AND_TOOLS),
    IRON_SHOVEL("minecraft:iron_shovel", "Iron Shovel", ItemCategory.WEAPONS_AND_TOOLS),
    DIAMOND_SHOVEL("minecraft:diamond_shovel", "Diamond Shovel", ItemCategory.WEAPONS_AND_TOOLS),
    IRON_HOE("minecraft:iron_hoe", "Iron Hoe", ItemCategory.WEAPONS_AND_TOOLS),
    DIAMOND_HOE("minecraft:diamond_hoe", "Diamond Hoe", ItemCategory.WEAPONS_AND_TOOLS),
    IRON_SWORD("minecraft:iron_sword", "Iron Sword", ItemCategory.WEAPONS_AND_TOOLS),
    DIAMOND_SWORD("minecraft:diamond_sword", "Diamond Sword", ItemCategory.WEAPONS_AND_TOOLS),
    BELL("minecraft:bell", "Bell", ItemCategory.WEAPONS_AND_TOOLS),

    // --- ARMADURAS Y CUERO (Armorer / Leatherworker) ---
    LEATHER("minecraft:leather", "Leather", ItemCategory.ARMOR_AND_APPAREL),
    LEATHER_HELMET("minecraft:leather_helmet", "Leather Cap", ItemCategory.ARMOR_AND_APPAREL),
    LEATHER_CHESTPLATE("minecraft:leather_chestplate", "Leather Tunic", ItemCategory.ARMOR_AND_APPAREL),
    LEATHER_LEGGINGS("minecraft:leather_leggings", "Leather Pants", ItemCategory.ARMOR_AND_APPAREL),
    LEATHER_BOOTS("minecraft:leather_boots", "Leather Boots", ItemCategory.ARMOR_AND_APPAREL),
    LEATHER_HORSE_ARMOR("minecraft:leather_horse_armor", "Leather Horse Armor", ItemCategory.ARMOR_AND_APPAREL),
    SADDLE("minecraft:saddle", "Saddle", ItemCategory.ARMOR_AND_APPAREL),
    IRON_HELMET("minecraft:iron_helmet", "Iron Helmet", ItemCategory.ARMOR_AND_APPAREL),
    IRON_CHESTPLATE("minecraft:iron_chestplate", "Iron Chestplate", ItemCategory.ARMOR_AND_APPAREL),
    IRON_LEGGINGS("minecraft:iron_leggings", "Iron Leggings", ItemCategory.ARMOR_AND_APPAREL),
    IRON_BOOTS("minecraft:iron_boots", "Iron Boots", ItemCategory.ARMOR_AND_APPAREL),
    CHAINMAIL_HELMET("minecraft:chainmail_helmet", "Chainmail Helmet", ItemCategory.ARMOR_AND_APPAREL),
    CHAINMAIL_CHESTPLATE("minecraft:chainmail_chestplate", "Chainmail Chestplate", ItemCategory.ARMOR_AND_APPAREL),
    CHAINMAIL_LEGGINGS("minecraft:chainmail_leggings", "Chainmail Leggings", ItemCategory.ARMOR_AND_APPAREL),
    CHAINMAIL_BOOTS("minecraft:chainmail_boots", "Chainmail Boots", ItemCategory.ARMOR_AND_APPAREL),
    DIAMOND_HELMET("minecraft:diamond_helmet", "Diamond Helmet", ItemCategory.ARMOR_AND_APPAREL),
    DIAMOND_CHESTPLATE("minecraft:diamond_chestplate", "Diamond Chestplate", ItemCategory.ARMOR_AND_APPAREL),
    DIAMOND_LEGGINGS("minecraft:diamond_leggings", "Diamond Leggings", ItemCategory.ARMOR_AND_APPAREL),
    DIAMOND_BOOTS("minecraft:diamond_boots", "Diamond Boots", ItemCategory.ARMOR_AND_APPAREL),
    SHIELD("minecraft:shield", "Shield", ItemCategory.ARMOR_AND_APPAREL),

    // --- PESCA Y OCÉANO (Fisherman) ---
    RAW_COD("minecraft:cod", "Raw Cod", ItemCategory.FISH_AND_OCEAN),
    COOKED_COD("minecraft:cooked_cod", "Cooked Cod", ItemCategory.FISH_AND_OCEAN),
    RAW_SALMON("minecraft:salmon", "Raw Salmon", ItemCategory.FISH_AND_OCEAN),
    COOKED_SALMON("minecraft:cooked_salmon", "Cooked Salmon", ItemCategory.FISH_AND_OCEAN),
    TROPICAL_FISH("minecraft:tropical_fish", "Tropical Fish", ItemCategory.FISH_AND_OCEAN),
    PUFFERFISH("minecraft:pufferfish", "Pufferfish", ItemCategory.FISH_AND_OCEAN),
    BOAT("minecraft:oak_boat", "Oak Boat", ItemCategory.FISH_AND_OCEAN),
    FISHING_ROD("minecraft:fishing_rod", "Fishing Rod", ItemCategory.FISH_AND_OCEAN),
    CAMPFIRE("minecraft:campfire", "Campfire", ItemCategory.FISH_AND_OCEAN),

    // --- CONSTRUCCIÓN Y LANA (Mason / Shepherd) ---
    CLAY_BALL("minecraft:clay_ball", "Clay Ball", ItemCategory.BLOCKS_AND_DECORATION),
    BRICK("minecraft:brick", "Brick", ItemCategory.BLOCKS_AND_DECORATION),
    STONE("minecraft:stone", "Stone", ItemCategory.BLOCKS_AND_DECORATION),
    GRANITE("minecraft:granite", "Granite", ItemCategory.BLOCKS_AND_DECORATION),
    DIORITE("minecraft:diorite", "Diorite", ItemCategory.BLOCKS_AND_DECORATION),
    ANDESITE("minecraft:andesite", "Andesite", ItemCategory.BLOCKS_AND_DECORATION),
    DRIPSTONE_BLOCK("minecraft:dripstone_block", "Dripstone Block", ItemCategory.BLOCKS_AND_DECORATION),
    POINTED_DRIPSTONE("minecraft:pointed_dripstone", "Pointed Dripstone", ItemCategory.BLOCKS_AND_DECORATION),
    CHISELED_STONE_BRICKS("minecraft:chiseled_stone_bricks", "Chiseled Stone Bricks", ItemCategory.BLOCKS_AND_DECORATION),
    POLISHED_GRANITE("minecraft:polished_granite", "Polished Granite", ItemCategory.BLOCKS_AND_DECORATION),
    POLISHED_DIORITE("minecraft:polished_diorite", "Polished Diorite", ItemCategory.BLOCKS_AND_DECORATION),
    POLISHED_ANDESITE("minecraft:polished_andesite", "Polished Andesite", ItemCategory.BLOCKS_AND_DECORATION),
    QUARTZ_BLOCK("minecraft:quartz_block", "Block of Quartz", ItemCategory.BLOCKS_AND_DECORATION),
    QUARTZ_PILLAR("minecraft:quartz_pillar", "Quartz Pillar", ItemCategory.BLOCKS_AND_DECORATION),
    TERRACOTTA("minecraft:terracotta", "Terracotta", ItemCategory.BLOCKS_AND_DECORATION),
    GLAZED_TERRACOTTA("minecraft:white_glazed_terracotta", "Glazed Terracotta", ItemCategory.BLOCKS_AND_DECORATION),
    WHITE_WOOL("minecraft:white_wool", "White Wool", ItemCategory.BLOCKS_AND_DECORATION),
    BLACK_WOOL("minecraft:black_wool", "Black Wool", ItemCategory.BLOCKS_AND_DECORATION),
    SHEARS("minecraft:shears", "Shears", ItemCategory.BLOCKS_AND_DECORATION),
    PAINTING("minecraft:painting", "Painting", ItemCategory.BLOCKS_AND_DECORATION);

    override fun toString(): String = displayName

    companion object {
        // Mapas en caché para búsquedas en tiempo O(1)
        private val cachePorNombre: Map<String, Item> = entries.associateBy { it.name.lowercase() }
        private val cachePorDisplay: Map<String, Item> = entries.associateBy { it.displayName.lowercase() }
        private val cachePorMcId: Map<String, Item> = entries.associateBy { it.minecraftId.lowercase() }

        /**
         * Busca un ítem involucrado en tradeos según su nombre de enum, displayName o ID de Minecraft.
         */
        fun buscarPorNombre(query: String): Item? {
            val q = query.trim().lowercase()
            return cachePorNombre[q]
                ?: cachePorDisplay[q]
                ?: cachePorMcId[q]
                ?: cachePorMcId["minecraft:$q"]
        }
    }
}