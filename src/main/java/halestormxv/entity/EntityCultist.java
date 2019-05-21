package halestormxv.entity;

import halestormxv.init.ItemInit;
import halestormxv.utility.handlers.LootTableHandler;
import halestormxv.utility.handlers.SoundsHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityCultist extends EntityMob implements IRangedAttackMob
{
    public EntityCultist(World worldIn)
    {
        super(worldIn);

        //AI Component
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackRanged(this, 1.25D, 60, 32.0F));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();

        this.setSize(0.6f, 1.5f);
        this.setSwingingArms(true);
    }

    protected void applyEntityAI()
    {
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityPigZombie.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableHandler.CULTIST;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(32);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);
        if (this.rand.nextFloat() < (this.world.getDifficulty() == EnumDifficulty.HARD ? 0.05F : 0.01F))
        {
            int i = this.rand.nextInt(3);
            if (i == 0)
            {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.ENCHANTED_BOOK));
            }
            else
            {
                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.ENCHANTED_BOOK));
            }
        }
    }

    @Override
    protected float getSoundPitch() { return 1.0f; }

    @Override
    protected SoundEvent getAmbientSound() { return SoundsHandler.ENTITY_CULTIST_AMBIENT; }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundsHandler.ENTITY_CULTIST_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return SoundsHandler.ENTITY_CULTIST_HURT;
    }

    protected SoundEvent getStepSound()
    {
        return null;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public void onLivingUpdate()
    {
        {
            if (this.world.isRemote)
            {
                for (int i = 0; i < 2; ++i)
                {
                    this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
                }
            }

            this.isJumping = false;
            super.onLivingUpdate();
        }
    }

    @Override
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
    }

    @Override
    protected Item getDropItem()
    {
        return ItemInit.DUST_SIEGRE;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        return livingdata;
    }

    @Override
    public float getEyeHeight()
    {
        float f = 1.34F;
        return f;
    }

    public void attackEntityWithRangedAttack(EntityLivingBase entity, float distance)
    {
        double d0 = entity.posX - this.posX;
        double d1 = entity.getEntityBoundingBox().minY + (double) (entity.height / 2.0F) - (this.posY + (double) (this.height / 2.0F));
        double d2 = entity.posZ - this.posZ;
        float f1 = MathHelper.sqrt(distance) * 0.5F;
        int randomChance = rand.nextInt(100) + 1;
        if (randomChance > 5)
        {
            for (int i = 0; i < 3; ++i) {
                EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.world, this, d0 + this.rand.nextGaussian() * (double) f1, d1, d2 + this.rand.nextGaussian() * (double) f1);
                entitysmallfireball.posY = this.posY + (double) (this.height / 2.0F) + 0.5D;
                this.world.spawnEntity(entitysmallfireball);
            }
            this.world.playEvent((EntityPlayer) null, 1018, new BlockPos((int) this.posX, (int) this.posY, (int) this.posZ), 0);
        }
        else if ( (randomChance < 5) && (this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ))) )
        {
            world.playSound(null, new BlockPos((int) this.posX, (int) this.posY, (int) this.posZ), SoundsHandler.ENTITY_CULTIST_SPECIAL, SoundCategory.AMBIENT, 1.0F, 1.0F);
            for (int i = 0; i < 4; ++i)
            {
                EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.world, this, d0 + this.rand.nextGaussian() * (double) f1, d1, d2 + this.rand.nextGaussian() * (double) f1);
                entitylargefireball.posY = this.posY + (double) (this.height / 2.0F) + 0.5D;
                this.world.spawnEntity(entitylargefireball);
            }
        }
    }

    public void setSwingingArms(boolean swingingArms) { }
}
