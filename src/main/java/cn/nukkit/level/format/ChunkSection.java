package cn.nukkit.level.format;

import cn.nukkit.api.DeprecationDetails;
import cn.nukkit.api.PowerNukkitOnly;
import cn.nukkit.api.Since;
import cn.nukkit.block.Block;
import cn.nukkit.blockstate.BlockState;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.BinaryStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
@ParametersAreNonnullByDefault
public interface ChunkSection {
    int getY();

    int getBlockId(int x, int y, int z);

    @PowerNukkitOnly
    int getBlockId(int x, int y, int z, int layer);

    void setBlockId(int x, int y, int z, int id);

    @Deprecated
    @DeprecationDetails(reason = "The data is limited to 32 bits", replaceWith = "getBlockState", since = "1.4.0.0-PN")
    int getBlockData(int x, int y, int z);

    @PowerNukkitOnly
    int getBlockData(int x, int y, int z, int layer);

    @Deprecated
    @DeprecationDetails(reason = "The data is limited to 32 bits", replaceWith = "getBlockState", since = "1.4.0.0-PN")
    void setBlockData(int x, int y, int z, int data);

    @Deprecated
    @DeprecationDetails(reason = "The data is limited to 32 bits", replaceWith = "getBlockState", since = "1.4.0.0-PN")
    @PowerNukkitOnly
    void setBlockData(int x, int y, int z, int layer, int data);

    @Deprecated
    @DeprecationDetails(reason = "Does not support hyper ids", since = "1.3.0.0-PN")
    int getFullBlock(int x, int y, int z);

    @PowerNukkitOnly
    @Deprecated
    @DeprecationDetails(reason = "Does not support hyper ids", since = "1.3.0.0-PN")
    int getFullBlock(int x, int y, int z, int layer);
    
    @PowerNukkitOnly
    @Since("1.3.0.0-PN")
    @Nonnull
    default BlockState getBlockState(int x, int y, int z) {
        return getBlockState(x, y, z, 0);
    }
    
    @PowerNukkitOnly
    @Since("1.4.0.0-PN")
    @Nonnull
    default BlockState getBlockState(int x, int y, int z, int layer) {
        return new BlockState(getBlockId(x, y, z, layer), getBlockData(x, y, z, layer));
    }

    @PowerNukkitOnly
    @Nonnull
    Block getAndSetBlock(int x, int y, int z, int layer, Block block);

    @Nonnull
    Block getAndSetBlock(int x, int y, int z, Block block);

    @PowerNukkitOnly
    void setBlockId(int x, int y, int z, int layer, int id);

    @Deprecated
    @DeprecationDetails(reason = "Does not support hyper ids", since = "1.3.0.0-PN", replaceWith = "setBlock(int x, int y, int z, int blockId, int meta)")
    boolean setFullBlockId(int x, int y, int z, int fullId);

    @Deprecated
    @DeprecationDetails(reason = "Does not support hyper ids", since = "1.3.0.0-PN", replaceWith = "setBlock(int x, int y, int z, int blockId, int meta)")
    boolean setFullBlockId(int x, int y, int z, int layer, int fullId);

    @PowerNukkitOnly
    boolean setBlockAtLayer(int x, int y, int z, int layer, int blockId);

    boolean setBlock(int x, int y, int z, int blockId);

    @Deprecated
    @DeprecationDetails(reason = "The data is limited to 32 bits", replaceWith = "getBlockState", since = "1.4.0.0-PN")
    boolean setBlock(int x, int y, int z, int blockId, int meta);

    @Deprecated
    @DeprecationDetails(reason = "The data is limited to 32 bits", replaceWith = "getBlockState", since = "1.4.0.0-PN")
    @PowerNukkitOnly
    boolean setBlockAtLayer(int x, int y, int z, int layer, int blockId, int meta);

    int getBlockSkyLight(int x, int y, int z);

    void setBlockSkyLight(int x, int y, int z, int level);

    int getBlockLight(int x, int y, int z);

    void setBlockLight(int x, int y, int z, int level);
    
    byte[] getSkyLightArray();

    byte[] getLightArray();

    boolean isEmpty();

    void writeTo(BinaryStream stream);

    @PowerNukkitOnly
    int getMaximumLayer();

    @PowerNukkitOnly
    @Nonnull
    CompoundTag toNBT();

    @Nonnull
    ChunkSection copy();
    
    @PowerNukkitOnly("Needed for level backward compatibility")
    @Since("1.3.0.0-PN")
    default int getContentVersion() {
        return 0;
    }

    @PowerNukkitOnly("Needed for level backward compatibility")
    @Since("1.3.0.2-PN")
    default void setContentVersion(int contentVersion) {
        // Does nothing
    }
    
    @PowerNukkitOnly()
    @Since("1.4.0.0-PN")
    default boolean hasBlocks() {
        return !isEmpty();
    }

    boolean setBlockStateAtLayer(int x, int y, int z, int layer, BlockState state);
    
    default boolean setBlockState(int x, int y, int z, BlockState state) {
        return setBlockStateAtLayer(x, y, z, 0, state);
    }
}
