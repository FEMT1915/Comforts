package voronoiaoc.byg.common.biomes.beachbiomes;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import voronoiaoc.byg.common.world.feature.biomefeatures.BYGFeatures;
import voronoiaoc.byg.core.byglists.BYGSBList;

public class RockyBeach extends Biome {
    static final ConfiguredSurfaceBuilder SURFACE_BUILDER = new ConfiguredSurfaceBuilder<>(BYGSBList.ROCKYBEACH_SB, BYGSBList.BYGSBConfigList.ROCKY);
    static final RainType PRECIPATATION = RainType.RAIN;
    static final Category CATEGORY = Category.BEACH;
    static final double DEPTH = 0.0F;
    static final double SCALE = 0.025F;
    static final float TEMPERATURE = 0.25F;
    static final float DOWNFALL = 0.4F;
    static final int WATER_COLOR = 4159204;
    static final int WATER_FOG_COLOR = 329011;
    static final String PARENT = null;

    public RockyBeach() {
        super(new Builder().surfaceBuilder(SURFACE_BUILDER).precipitation(PRECIPATATION).category(CATEGORY).depth((float) DEPTH).func_235097_a_((new BiomeAmbience.Builder()).func_235246_b_(WATER_COLOR).func_235248_c_(WATER_FOG_COLOR).func_235239_a_(12638463).func_235243_a_(MoodSoundAmbience.field_235027_b_).func_235238_a_()).scale((float) SCALE).temperature(TEMPERATURE).downfall(DOWNFALL).parent(PARENT));//this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        //this.addStructure(Feature.BURIED_TREASURE.withConfiguration(new BuriedTreasureConfig(0.01F)));
        //this.addStructure(Feature.SHIPWRECK.withConfiguration(new ShipwreckConfig(true)));
        DefaultBiomeFeatures.addCarvers(this);

        DefaultBiomeFeatures.addMonsterRooms(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addSedimentDisks(this);
        DefaultBiomeFeatures.addSparseGrass(this);
        DefaultBiomeFeatures.addSprings(this);
        BYGFeatures.addBeachGrass(this);
        BYGFeatures.addRockyStoneBoulder(this);

        this.addSpawn(EntityClassification.AMBIENT, new SpawnListEntry(EntityType.BAT, 10, 8, 8));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 5, 1, 1));


    }

    @Override
    public Biome getRiver() {
        return this;
    }
}