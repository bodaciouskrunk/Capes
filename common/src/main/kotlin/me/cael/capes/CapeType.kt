package me.cael.capes

import com.mojang.authlib.GameProfile
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text

enum class CapeType(val stylized: String) {
    MINECRAFT("Minecraft"), MINECRAFTCAPES("MinecraftCapes"), OPTIFINE("OptiFine"), LABYMOD("LabyMod"), WYNNTILS("Wynntils"), COSMETICA("Cosmetica"), CLOAKSPLUS("Cloaks+");

    fun cycle() = when(this) {
        MINECRAFT -> MINECRAFTCAPES
        MINECRAFTCAPES -> OPTIFINE
        OPTIFINE -> LABYMOD
        LABYMOD -> WYNNTILS
        WYNNTILS -> COSMETICA
        COSMETICA -> CLOAKSPLUS
        CLOAKSPLUS -> MINECRAFT
    }

    fun getURL(profile: GameProfile): String? {
        val config = Capes.CONFIG
        return when (this) {
            MINECRAFTCAPES -> if(config.enableMinecraftCapesMod) "https://api.minecraftcapes.net/profile/${profile.id.toString().replace("-", "")}" else null
            OPTIFINE -> if(config.enableOptifine) "http://s.optifine.net/capes/${profile.name}.png" else null
            LABYMOD -> if(config.enableLabyMod) "https://dl.labymod.net/capes/${profile.id}" else null
            WYNNTILS -> if(config.enableWynntils) "https://athena.wynntils.com/user/getInfo" else null
            COSMETICA -> if(config.enableCosmetica) "https://api.cosmetica.cc/get/cloak?username=${profile.name}&uuid=${profile.id}&nothirdparty" else null
            CLOAKSPLUS -> if(config.enableCloaksPlus) "http://161.35.130.99/capes/${profile.name}.png" else null
            MINECRAFT -> null
        }
    }

    fun getToggleText(enabled: Boolean): Text = ScreenTexts.composeToggleText(Text.of(stylized), enabled)

    fun getText(): Text = Text.translatable("options.capes.capetype", stylized)

}
